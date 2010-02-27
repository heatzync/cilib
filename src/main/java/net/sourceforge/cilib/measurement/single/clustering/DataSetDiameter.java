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
package net.sourceforge.cilib.measurement.single.clustering;

import net.sourceforge.cilib.algorithm.Algorithm;
import net.sourceforge.cilib.measurement.Measurement;
import net.sourceforge.cilib.problem.dataset.StaticDataSetBuilder;
import net.sourceforge.cilib.type.types.Real;
import net.sourceforge.cilib.type.types.Type;
import net.sourceforge.cilib.util.ClusteringUtils;

/**
 * Calculates the data set diameter as the maximum distance between any two patterns in the data set. The
 * {@link StaticDataSetBuilder} already cached the distances from every pattern to every other pattern using its own
 * customisable {@link DistanceMeasure}.
 *
 * @author Theuns Cloete
 */
public class DataSetDiameter implements Measurement {
    private Real xMax = null;

    @Override
    public Measurement getClone() {
        return this;
    }

    @Override
    public String getDomain() {
        return "R";
    }

    @Override
    public Type getValue(Algorithm algorithm) {
        // we only have to calculate it once as long as the data set remains static/unchanged
        if (xMax == null) {
            ClusteringUtils helper = ClusteringUtils.get();
            StaticDataSetBuilder dataSetBuilder = helper.getDataSetBuilder();
            int numPatterns = helper.getNumberOfPatternsInDataSet();
            double maxDistance = 0.0;

            for (int y = 0; y < numPatterns - 1; y++) {
                for (int x = y + 1; x < numPatterns; x++) {
                    maxDistance = Math.max(maxDistance, dataSetBuilder.getCachedDistance(x, y));
                }
            }
            this.xMax = Real.valueOf(maxDistance);
        }
        return this.xMax;
    }
}
