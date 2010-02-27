/**
 * Computational Intelligence Library (CIlib)
 * Copyright (C) 2003 - 2010
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.cilib.functions.clustering.validityindices;

import net.sourceforge.cilib.functions.clustering.ClusteringFitnessFunction;
import net.sourceforge.cilib.problem.dataset.StaticDataSetBuilder;
import net.sourceforge.cilib.type.types.container.Pattern;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * MaulikBandyopadhyayIndex.
 *
 * This is the I(K) Validity Index as given in Equation 13 in Section IV on page 124 of:<br/>
 * @Article{ 923275, title = "Nonparametric Genetic Clustering: Comparison of Validity Indices",
 *           author = "Ujjwal Maulik and Sanghamitra Bandyopadhyay", journal = "IEEE Transactions on
 *           Systems, Man, and Cybernetics, Part C: Applications and Reviews", pages = "120--125",
 *           volume = "31", number = "1", month = feb, year = "2001", issn = "1094-6977", }
 * NOTE: I(K) isn't really a name, so I'm calling it the Maulik-Bandyopadhyay Validity Index
 * NOTE: By default, the cluster center refers to the cluster centroid. See {@link ClusterCenterStrategy}.
 * @author Theuns Cloete
 */
public class MaulikBandyopadhyayIndex extends ClusteringFitnessFunction {
    private static final long serialVersionUID = -1094819834873604274L;
    private int p = 1;

    public MaulikBandyopadhyayIndex() {
        p = 1;
    }

    @Override
    public MaulikBandyopadhyayIndex getClone() {
        return new MaulikBandyopadhyayIndex();
    }

    @Override
    public double calculateFitness() {
        return Math.pow(termOne() * termTwo() * termThree(), p);
    }

    private double termOne() {
        return 1.0 / this.clustersFormed;
    }

    private double termTwo() {
        // This is the normalizing factor, E_1 which they talk about in the article.
        double intraDatasetDistance = 0.0;

        /* E_1 refers to the intra-cluster distance when the dataset is clustered using only one
         * cluster. In this case, the dataset mean can be thought of as the dataset's centroid as
         * well.
         */
        Vector mean = ((StaticDataSetBuilder) this.problem.getDataSetBuilder()).getMean();
        for (Pattern<Vector> pattern : ((StaticDataSetBuilder) this.problem.getDataSetBuilder()).getPatterns()) {
            intraDatasetDistance += this.problem.calculateDistance(pattern.getData(), mean);
        }

        return intraDatasetDistance / this.calculateIntraClusterDistance();
    }

    private double termThree() {
        return this.calculateMaximumInterClusterDistance();
    }

    public void setP(int pu) {
        if (pu < 1)
            throw new IllegalArgumentException("The p-value cannot be < 1");
        p = pu;
    }
}
