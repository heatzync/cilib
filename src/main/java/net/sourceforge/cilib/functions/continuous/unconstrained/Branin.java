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
package net.sourceforge.cilib.functions.continuous.unconstrained;

import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * <p><b>Booth Function</b></p>
 *
 * <p><b>Reference:</b> X. Yao, Y. Liu, G. Liu, <i>Evolutionary Programming Made Faster</i>,
 * IEEE Transactions on Evolutionary Computation, 3(1):82--102, 1999</p>
 *
 * <p>Minimum:
 * <ul>
 * <li> &fnof;(<b>x</b>*) = 0.397887 </li>
 * <li> <b>x</b>* = (-&pi;, 12.275), (&pi;, 2.275), (9.425, 2.425)</li>
 * <li> for x<sub>1</sub> in [-5,10], x<sub>2</sub> in [0,15]</li>
 * </ul>
 * </p>
 *
 * <p>Characteristics:
 * <ul>
 * <li>Only defined for 2 dimensions</li>
 * <li>Has 3 global minima</li>
 * <li>Unimodal</li>
 * <li>Seperable</li>
 * <li>Regular</li>
 * </ul>
 * </p>
 *
 * R(-5,10),R(0,15)
 *
 * @author Clive Naicker
 *
 */
public class Branin implements ContinuousFunction {

    private static final long serialVersionUID = -2254223453957430344L;
    private double a = 1.0;
    private double b = 5.1 / (4 * Math.PI * Math.PI);
    private double c = 5.0 / Math.PI;
    private double d = 6.0;
    private double e = 10.0;
    private double f = 1.0 / (8.0 * Math.PI);

    /**
     * Create a new instance of {@linkplain Branin}. Domain defaults to: <code>R(-5,10),R(0,15)</code>
     */
    public Branin() {
        a = 1.0;
        b = 5.1 / (4 * Math.PI * Math.PI);
        c = 5.0 / Math.PI;
        d = 6.0;
        e = 10.0;
        f = 1.0 / (8.0 * Math.PI);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double apply(Vector input) {
        double x1 = input.doubleValueOf(0);
        double x2 = input.doubleValueOf(1);

        return a * Math.pow((x2 - b * x1 * x1 + c * x1 - d), 2) + e * (1 - f) * Math.cos(x1) + e;
    }
}
