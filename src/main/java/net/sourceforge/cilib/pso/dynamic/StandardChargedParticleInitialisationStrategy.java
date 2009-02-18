/*
 * Copyright (C) 2003 - 2008
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.sourceforge.cilib.pso.dynamic;


/**
 * @author Anna Rakitianskaia
 *
 */
public class StandardChargedParticleInitialisationStrategy implements
		ChargedParticleInitialisationStrategy {

	private static final long serialVersionUID = -652103945949329612L;
	private double chargedRatio; // determines the percentage of the swarm that is to be charged
	private double chargeMagnitude; // charge magnitude
	private static int populationSize;
	private static int chargedCounter = 0; // maybe a bad idea, but this variable keeps track of the number of particles already charged

	public StandardChargedParticleInitialisationStrategy() {
		// defaults:
		chargedRatio = 0.5;	// one half of the swarm is charged => Atomic swarm
		chargeMagnitude = 16; // the obscure value 16 comes from the article where the chraged PSO was analysed for the 1st time by its creators
	}

	public StandardChargedParticleInitialisationStrategy(StandardChargedParticleInitialisationStrategy copy) {
		this.chargedRatio = copy.chargedRatio;
		this.chargeMagnitude = copy.chargeMagnitude;
	}

	public StandardChargedParticleInitialisationStrategy getClone() {
		return new StandardChargedParticleInitialisationStrategy(this);
	}

	/* (non-Javadoc)
	 * @see net.sourceforge.cilib.pso.particle.initialisation.ChargedParticleInitialisationStrategy#initialise(net.sourceforge.cilib.pso.particle.ChargedParticle)
	 */
	public void initialise(ChargedParticle particle) {
		if(chargedCounter < Math.floor(populationSize*chargedRatio)) {
			particle.setCharge(chargeMagnitude);
			++chargedCounter;
		}
		else {
			particle.setCharge(0);
		}
	}


	/**
	 * @return the chargedRatio
	 */
	public double getChargedRatio() {
		return chargedRatio;
	}


	/**
	 * @param chargedRatio the chargedRatio to set
	 */
	public void setChargedRatio(double chargedRatio) {
		this.chargedRatio = chargedRatio;
	}

	/**
	 * @return the chargeMagnitude
	 */
	public double getChargeMagnitude() {
		return chargeMagnitude;
	}

	/**
	 * @param chargeMagnitude the chargeMagnitude to set
	 */
	public void setChargeMagnitude(double chargeMagnitude) {
		this.chargeMagnitude = chargeMagnitude;
	}

	/**
	 * @param populationSize the populationSize to set
	 */
	public void setPopulationSize(int populationSize) {
		StandardChargedParticleInitialisationStrategy.populationSize = populationSize;
	}

}
