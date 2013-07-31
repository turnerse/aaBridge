/*******************************************************************************
 * Copyright (c) 2013 Roger Pfister.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Roger Pfister - initial API and implementation
 ******************************************************************************/
package com.rogerpf.aabridge.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.rogerpf.aabridge.controller.Aaa;
import com.rogerpf.aabridge.controller.App;
import com.rogerpf.aabridge.model.Card;
import com.rogerpf.aabridge.model.Hand;
import com.rogerpf.aabridge.model.Zzz;
import com.rpsd.bridgefonts.BridgeFonts;

/**   
 */
public class TricksFourDisplayPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean showCompletedTrick = false;
	public boolean showThinBox = true;
	// public boolean finalCardSpecial = false;

	int endOfTrickDownCounter = 0;

	// -----------------------------
	class Suggestion {
		int rank;
		int suit;

		Suggestion() { /* Constructor */
			clear();
		}

		void clear() {
			rank = -1;
			suit = -1;
		}
	}

	Suggestion[] suggestions = new Suggestion[] { new Suggestion(), new Suggestion(), new Suggestion(), new Suggestion() };

	/**
	 */
	TricksFourDisplayPanel() { /* Constructor */
		setVisible(false);
	}

	/**
	*/
	public Timer normalTrickDisplayTimer = new Timer(App.playPluseTimerMs, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			if (App.frame.isSplashTimerRunning())
				return; // wait until the splash has cleared
			normalTrickDisplayTimer.stop();
			normalTrickDisplayTimer.setInitialDelay(App.playPluseTimerMs);

			// App.deal.hideFinish = false; // just in case

			if (App.deal.isPlaying() && App.isMode(Aaa.NORMAL)) {
				Hand hand = App.deal.getNextHandToPlay();

				if (showCompletedTrick) {

					// End of trick - click to continue
					if (App.isPauseAtEotClickWanted() && App.isPauseAtEotClickWanted() && App.isAutoPlay(hand.compass)) {
						// go around again - we are waiting for showCompletedTrick to be cleared by mouse click
						App.gbp.c1_1__tfdp.normalTrickDisplayTimer_startIfNeeded();
						return;
					}

					if (App.isAutoPlay(hand.compass) && (App.eotExtendedDisplay > 0)) {
						if (endOfTrickDownCounter == 0) {
							endOfTrickDownCounter = 1 + App.eotExtendedDisplay;
						}
						if (--endOfTrickDownCounter > 0) {
							App.gbp.c1_1__tfdp.normalTrickDisplayTimer_startIfNeeded();
							return;
						}
						showCompletedTrick = false;
					}
				}

				int played = App.deal.countCardsPlayed();
				if (played == 52) {
					return; // happens because we are messing with 'isFinshed()'
				}

				if (played == 51) {
					App.deal.hideFinish = true;
				}

				if (App.isAutoPlay(hand.compass)) {
					App.con.autoPlayRequest(hand);
				}
				else {
					App.con.selfPlayOpportunity(hand);
					showThinBox = true;
					repaint();
					App.gbp.hdps[hand.compass].repaint();
				}

				played = App.deal.countCardsPlayed();
				if (played == 52) {
					App.deal.hideFinish = true; // Yes well - I know it is already set to true
					finalCardUnDisplayTimer.start();
				}
				else if (App.deal.hideFinish == true) {
					App.deal.hideFinish = false; // the self play opportunity did not happen
					// App.gbp.repaint();
				}

			}
		}
	});

	/**
	*/
	public Timer finalCardUnDisplayTimer = new Timer(App.finalCardTimerMs, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			finalCardUnDisplayTimer.stop();
			// finalCardUnDisplayTimer.setInitialDelay(App.finalCardTimerMs);
			App.deal.hideFinish = false;
			App.gbp.matchPanelsToDealState();
			App.gbp.repaint();
		}
	});

	/**
	*/
	public Timer reviewTrickDisplayTimer = new Timer(App.playPluseTimerMs, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			reviewTrickDisplayTimer.stop();
			reviewTrickDisplayTimer.setInitialDelay(App.playPluseTimerMs);

			if (App.isMode(Aaa.REVIEW_PLAY)) {
				App.con.reviewTrickDisplayTimerFired();
			}
		}
	});

	/**
	 */
	public void normalTrickDisplayTimer_startIfNeeded() {
		if (App.deal.isPlaying()) {
			normalTrickDisplayTimer.start();
			showThinBox = false;
		}
	}

	/**   
	 */
	public int getSuggestedRank(int phyScreenPos) {
		int compass = App.compassFromPhyScreenPos(phyScreenPos);
		return suggestions[compass].rank;
	}

	/**   
	 */
	public void setSuggestedRank(int phyScreenPos, int rank) {
		int compass = App.compassFromPhyScreenPos(phyScreenPos);
		suggestions[compass].rank = rank;
	}

	/**   
	 */
	public int getSuggestedSuit(int phyScreenPos) {
		int compass = App.compassFromPhyScreenPos(phyScreenPos);
		return suggestions[compass].suit;
	}

	/**   
	 */
	public void setSuggestedSuit(int phyScreenPos, int suit) {
		int compass = App.compassFromPhyScreenPos(phyScreenPos);
		suggestions[compass].suit = suit;
	}

	/**   
	 */
	public void dealDirectionChange() {
	}

	/**   
	 */
	public void dealMajorChange() {
	}

	/**   
	 */
	public void clearAllCardSuggestions() {
		for (Suggestion sug : suggestions) {
			sug.clear();
		}
	}

	/**   
	 */
	public void makeCardSuggestions() {
		Hand hand = App.deal.getNextHandToPlay();
		if (hand == null)
			return; // end of board?
		int suit = hand.makeSuitSuggestion();
		// if (suit > -1) {
		suggestions[hand.compass].suit = suit;
		// }
	}

	/**   
	 */
	public void ClearShowCompletedTrick() {
		if (showCompletedTrick) {
			showCompletedTrick = false;
			repaint();
		}
	}

	/**   
	 */
	public void SetShowCompletedTrick() {
		showCompletedTrick = true;
	}

	/**   
	 */
	public void paintComponent(Graphics g) { // TrickDisplayPanel

		super.paintComponent(g);
		setBackground(Aaa.baizeGreen);

		if (App.deal.isBidding()) // !!!!!! should never be needed
			return;

		Graphics2D g2 = (Graphics2D) g;
		Aaa.commonGraphicsSettings(g2);

		Dimension panelSize = getSize();

		float panelWidth = (float) panelSize.width;
		float panelHeight = (float) panelSize.height;

		if (!App.isMode(Aaa.REVIEW_PLAY) && App.deal.isFinished()) {

			RoundRectangle2D.Float rr = new RoundRectangle2D.Float();

			float marginLeft = panelWidth * 0.09f;
			float marginTop = panelHeight * 0.18f;
			float marginBottom = panelHeight * 0.18f;
			float curve = panelWidth * 0.00f;

			float activityWidth = panelWidth - (marginLeft * 2);
			float activityHeight = panelHeight - (marginTop + marginBottom);

			float fontSize = activityHeight * 0.37f;

			int trickDiff = App.deal.getContractTrickCountSoFar().x - (6 + App.deal.contract.level);

			String text = "";
			if (App.deal.isDoneHand()) {
				g2.setColor(Aaa.diamondsColor);
				text = "Welcome";
			}
			else if (App.deal.contract == App.deal.PASS) {
				g2.setColor(Aaa.diamondsColor);
//				fontSize *= 0.8f; 
				text = "Passed Out";
			}
			else if (trickDiff > 0) {
				g2.setColor(Aaa.clubsColor);
				text = Integer.toString(trickDiff) + " Over";
			}
			else if (trickDiff == 0) {
				g2.setColor(Aaa.clubsColor);
				fontSize *= 1.1f;
				text = "Made";
			}
			else {
				g2.setColor(Aaa.heartsColor);
				text = Integer.toString(-trickDiff) + " Down";
			}

			// fill the lozenge ----------------------------------------------
			rr.setRoundRect(marginLeft, marginTop, activityWidth, activityHeight, curve, curve);
			g2.draw(rr);
			g2.fill(rr);
			g2.setFont(BridgeFonts.bridgeBoldFont.deriveFont(fontSize));
			g2.setColor(Color.white);
			Aaa.drawCenteredString(g2, text, marginLeft, marginTop, activityWidth, activityHeight * 0.7f);

			g2.setFont(BridgeFonts.bridgeBoldFont.deriveFont(activityHeight * 0.16f));

			text = "Click  - Next Board -  bottom left";
			Aaa.drawCenteredString(g2, text, marginLeft, marginTop + activityHeight * 0.5f, activityWidth, activityHeight * 0.6f);

			return;
		}

		// preset the as many common values as possible
		// ----------------------------------------------

		float marginLeft = panelWidth * 0.00f;
		float marginRight = panelWidth * 0.02f;
		float marginTop = panelHeight * 0.04f;
		float marginBottom = panelHeight * 0.03f;

		float activityWidth = panelWidth - (marginLeft + marginRight);
		float activityHeight = panelHeight - (marginTop + marginBottom);

		float cardWidth = activityWidth * 0.28f;
		float cardHeight = cardWidth * 0.65f;
		float curve = cardHeight * 0.50f;

		float blackLineWidth = activityWidth * 0.007f;
		float colorLineWidth = activityWidth * 0.010f;

		float faceFontSize = cardHeight * 0.78f;
		float symbolFontSize = cardHeight * 0.50f;

		Font faceFont = BridgeFonts.faceAndSymbFont.deriveFont(faceFontSize);
		Font symbolFont = BridgeFonts.faceAndSymbFont.deriveFont(symbolFontSize);

		// ------------------------------------------------------------------

		float boarderLeft = cardWidth * 0.17f;
		float boarderInLeft = cardWidth * 0.45f;
		float symbolBoarderBot = cardHeight * 0.28f;
		float faceBoarderBot = cardHeight * 0.20f;

		RoundRectangle2D.Float rr = new RoundRectangle2D.Float();

		// We display the cards as seen by the player in the physical Pos
		// 'South' seat (the target)

		int trickRequested = 0;
		Hand trickWinner = null;

		if (App.isMode(Aaa.REVIEW_PLAY)) {
			trickRequested = App.reviewTrick;
			if (trickRequested < 0)
				return;
			if (App.reviewCard % 4 == 0) {
				trickWinner = App.deal.prevTrickWinner.get(trickRequested); // was bug in 1362 had trickRequested + 1
			}
		}
		else { // normal mode
			trickRequested = App.deal.getCurTrickIndex();

			if (showCompletedTrick && (trickRequested > 0) && App.deal.isCurTrickComplete()) {
				trickWinner = App.deal.prevTrickWinner.get(trickRequested);
				trickRequested--;
			}
		}

		// Display the 'eot click required' indication (a dot)
		if (showCompletedTrick && App.isMode(Aaa.NORMAL) && App.isPauseAtEotClickWanted() && App.isAutoPlay(App.deal.getNextHandToPlay().compass)) {
			double x = marginLeft + activityWidth * 0.04;
			double y = marginTop + activityHeight * 0.90;
			g2.setColor(Aaa.eotDotColor);
			double diameter = activityWidth * 0.04;
			Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);
			g2.fill(circle);
		}

		// System.out.println( trickRequested );

		Point2D.Float leftTopPercent[] = new Point2D.Float[] { new Point2D.Float(0.38f, 0.00f), /* N */
		new Point2D.Float(0.61f, 0.28f), /* E */
		new Point2D.Float(0.38f, 0.57f), /* S */
		new Point2D.Float(0.16f, 0.28f) /* W */};

		Hand leader = App.deal.prevTrickWinner.get(trickRequested);

		int revInd = -1;
		for (int compass : Zzz.rota[leader.compass]) {
			revInd++;
			if (App.isMode(Aaa.REVIEW_PLAY) && (revInd >= App.reviewCard)) {
				return;
			}
			Hand hand = App.deal.hands[compass];
			assert (compass == hand.compass);

			int phyPos = App.phyScreenPosFromCompass(hand.compass);

			Card card = null;
			if (trickRequested < hand.played.size()) {
				card = hand.played.get(trickRequested);
			}

			if ((card == null)
					&& (App.isAutoPlay(hand.compass) || (App.deal.getNextHandToPlay() != hand) || ((App.deal.getNextHandToPlay() == hand) && (showThinBox == false)))) {
				return;
			}

			int rank = suggestions[hand.compass].rank;
			int suit = suggestions[hand.compass].suit;

			if (card != null) {
				suggestions[hand.compass].rank = -1;
				suggestions[hand.compass].suit = -1;
				rank = card.getRank();
				suit = card.getSuit();
			}

			float left = marginLeft + activityWidth * leftTopPercent[phyPos].x;
			float top = marginTop + activityHeight * leftTopPercent[phyPos].y;

			float symbolLeft = left + boarderLeft;
			float symbolBottom = top + cardHeight - symbolBoarderBot;

			float faceLeft = left + boarderInLeft;
			float faceBottom = top + cardHeight - faceBoarderBot;

			if (card == null) {
				// shink the the thin line lozenge as it can look too big -
				// optincal illusion
				float pc = 0.02f;
				left += cardWidth * pc;
				cardWidth -= cardWidth * 2f * pc;
				top += cardHeight * pc;
				cardHeight -= cardHeight * 2f * pc;
			}

			// fill the lozenge ----------------------------------------------
			rr.setRoundRect(left, top, cardWidth, cardHeight, curve, curve);

			if (card != null) {
				g2.setPaint(Color.white);
				g2.fill(rr);
			}

			Color cardColor = (suit < 0) ? Aaa.noChosenSuit : Aaa.cdhsSugColors[suit];
			g2.setColor(cardColor);

			float stkSize = (card == null) ? blackLineWidth : (colorLineWidth * ((hand == trickWinner) ? 2.5f : 1));
			g2.setStroke(new BasicStroke(stkSize));
			g2.draw(rr);

			// Suit
			if (0 <= suit && suit <= 3) {
				g2.setColor((card == null) ? cardColor : Aaa.cdhsWeakColors[suit]);
				g2.setFont(symbolFont);
				g2.drawString(Zzz.suit_to_cdhsntSt[suit], symbolLeft, symbolBottom);
			}

			// Rank
			if (2 <= rank && rank <= 14) {
				g2.setColor((card == null) ? cardColor : Aaa.cdhsColors[suit]);
				g2.setFont(faceFont);
				g2.drawString(Zzz.rank_to_rankSt[rank] + "", faceLeft, faceBottom);
			}
		}

	}

}
