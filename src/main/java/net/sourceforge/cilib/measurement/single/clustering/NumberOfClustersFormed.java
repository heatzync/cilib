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

import net.sourceforge.cilib.algorithm.AbstractAlgorithm;
import net.sourceforge.cilib.algorithm.Algorithm;
import net.sourceforge.cilib.measurement.Measurement;
import net.sourceforge.cilib.problem.ClusteringProblem;
import net.sourceforge.cilib.problem.dataset.DataSetBuilder;
import net.sourceforge.cilib.problem.dataset.StaticDataSetBuilder;
import net.sourceforge.cilib.type.types.Int;
import net.sourceforge.cilib.type.types.container.Vector;
import net.sourceforge.cilib.util.ClusteringUtils;

/**
 * This measurement measures the number of clusters that were formed during a particular clustering.
 * For this measurement to work, the following is important:
 * <ol>
 * <li><tt>Algorithm.get()</tt> should not be <tt>null</tt>.</li>
 * <li>The algorithm's best solution (best position) should return a {@link Vector}.</li>
 * <li>The algorithm's problem's {@link DataSetBuilder} should be a {@link StaticDataSetBuilder}.</li>
 * <li>The {@link ClusteringUtils#arrangeClustersAndCentroids(net.sourceforge.cilib.type.types.container.Vector)} method
 * should be implemented to remove <i>empty clusters</i>.</li>
 * <li>The {@link ClusteringUtils#getArrangedClusters()} method should be implemented to return the list of non-empty
 * clusters.</li>
 * </ol>
 *
 * @author Theuns Cloete
 */
public class NumberOfClustersFormed implements Measurement<Int> {
    private static final long serialVersionUID = 2174807313995885918L;

    /**
     * {@inheritDoc}
     */
    @Override
    public NumberOfClustersFormed getClone() {
        return this;
    }

    @Override
    public String getDomain() {
        return "Z";
    }

    @Override
    public Int getValue(Algorithm algorithm) {
        //TODO: When we start using Guice, this statement should be updated
        ClusteringProblem problem = (ClusteringProblem) AbstractAlgorithm.getAlgorithmList().get(0).getOptimisationProblem();
        StaticDataSetBuilder dataSetBuilder = (StaticDataSetBuilder) problem.getDataSetBuilder();
        Vector centroids = (Vector) algorithm.getBestSolution().getPosition();

        return Int.valueOf(ClusteringUtils.arrangeClustersAndCentroids(centroids, problem, dataSetBuilder).size());
    }
}