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
package com.rogerpf.aabridge.model;

public class Qstr_3rd {

	static void act(Strategy stra, Gather g) {
		Hand h = g.hand; // a gather is just a collection of data from the point of view of THIS hand

		// ****************************** STRATEGY - 3rd 3rd 3rd Declarer and Dummy (index 2) ******************************
		int brk = 0;
		if (g.trickNumb == 6)
			if ((g.compass == 0))
				brk++; // put your breakpoint here :)

		if (brk > 0)
			brk++; // put your breakpoint here :)

		if (g.drawTrumpsHint) {

			if (g.trumpsRunable && g.haveTrumps) {
				// play the trumps in the boring old normal way - so NO finnessing
				stra.add(new StraStep(Strategy.DrawTrumps, "", -1, -1, -1));
				return;
			}

			/** 
			 * we are NOT on lead but still want trumps to be drawn
			 */

			// is there a finnesse/positional play that we we should be taking NOW
			if (g.suitLed == g.trumpSuit) {
				if (g.haveTrumps && g.positionInTrick == g.positionInTrick) {
					if (Play_Mpat.isPatternMatch(g, g.trumpSuit, g.positionInTrick, Zzz.MatchAsSelf)) {
						stra.add(new StraStep(Strategy.PlayCard, "3rd in hand takes finnesse in trumps", g.mpatRtn.rankRel, g.trumpSuit, -1));
						return;
					}
				}
				// play the trumps in the boring old normal way - so NO finnessing
				stra.add(new StraStep(Strategy.DrawTrumps, "", -1, -1, -1));
				return;
			}

			// trumps were NOT led

			// is there a finnesse/positional play that we might like to take - IF WE WERE THE LEADER ?
			if (g.haveTrumps) {
				if (Play_Mpat.isPatternMatch(g, g.trumpSuit, Zzz.Leader_Pos, Zzz.MatchAsSelf)) {
					stra.add(new StraStep(Strategy.GetToHand, "", -1, -1, g.compass));
					return;
				}
			}

			// is there a trump finesse/positional play that we would like partner to take - if she were the leader ?
			if (Play_Mpat.isPatternMatch(g, g.trumpSuit, Zzz.Leader_Pos, Zzz.MatchAsPartner)) {
				stra.add(new StraStep(Strategy.GetToHand, "", -1, -1, g.partner.compass));
				return;
			}
		}

		/** 
		 * From here on this is all about which suit to best develop for traking tricks 
		 * 
		 * This is no trumps or trumps are drawn or a master is left with the opps or
		 * they have trump control
		 *  or you just have no entry to to the trump drawing hand - umm not so !
		 *  
		 *  So think NO TRUMPS
		 */

		if (g.ourTopTricksTot >= g.ourTarget) {
			stra.add(new StraStep(Strategy.RunTopTricksInSuit, "", -1, g.suitLed, -1));
		}

		if (brk > 0)
			brk++;

		/** 
		 * we are NOT on lead but may want to take the lead
		 */

		if (g.haveSuitLed) {
			//
			// Is there a finnesse/positional play that we we should be taking NOW
			//
			Play_Mpat.isPatternMatch(g, g.suitLed, g.positionInTrick, Zzz.MatchAsSelf);
			// FragAnal fa = g.fragAnals[g.suitLed];
			if (g.mpatRtn.matchEntryId > 0) {
				Card card = h.frags[g.suitLed].getIfRelExist(g.mpatRtn.rankRel);
				if (card == null) {
					@SuppressWarnings("unused")
					int x = 0; // put your breakpoint here
				}
				assert (card != null);
				System.out.println(Zzz.compass_to_nesw_st_long[h.compass] + "  TakeFinnesse non trump" + card);
				stra.add(new StraStep(Strategy.PlayCard, "3rd(2/4) in hand takes finnesse (not trumnps)", g.mpatRtn.rankRel, g.suitLed, -1));
				return;
			}
		}

		//
		// Is there a finnesse/positional play that we might like to take - IF WE WERE THE LEADER ?
		//
		for (FragAnal fa : g.fragAnals) {
			// results are stored in fa[suit].mpatRtn
			Play_Mpat.isPatternMatch(g, fa.suit, Zzz.Leader_Pos, Zzz.MatchAsSelf);
		}
		// now we can sort on this info
		g.sort_finnesseSuitablity(0);
		// well, do we have a good finnesse to take ?
		{
			FragAnal fa = g.fragAnals[0];
			if (fa.mpatRtn.rating > 0) {
				System.out.println(Zzz.compass_to_nesw_st_long[h.compass] + "  Get To Hand, try to win");
				stra.add(new StraStep(Strategy.GetToHand, "", -1, -1, g.compass));
				return;
			}
		}

		//
		// Is there a finesse/positional play that we would like partner to take - if she were the leader ?
		//
		for (FragAnal fa : g.fragAnals) {
			// results are stored in fa[suit].mpatRtn
			Play_Mpat.isPatternMatch(g, fa.suit, Zzz.Leader_Pos, Zzz.MatchAsPartner);
		}
		// now we can sort on this info
		g.sort_finnesseSuitablity(1);
		// well, do we have a good finnesse to take ?
		{
			FragAnal fa = g.fragAnals[0];
			if (fa.mpatRtn.rating > 0) {
				System.out.println(Zzz.compass_to_nesw_st_long[h.compass] + "  Get to Partners hand ");
				stra.add(new StraStep(Strategy.GetToHand, "", -1, -1, g.partner.compass));
				return;
			}
		}

	}

}