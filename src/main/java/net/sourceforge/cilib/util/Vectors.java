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
package net.sourceforge.cilib.util;

import com.google.common.base.Function;
import net.sourceforge.cilib.type.types.Numeric;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * Utility methods for {@linkplain Vector}s.
 */
public final class Vectors {

    /**
     * Default constructor. Specified constructor to be private so that an instance
     * of this utility class cannot be created.
     */
    private Vectors() {
    }

    /**
     * Constructs a {@link Vector} from <code>vector</code> Vector with each component's value
     * set to the upper bound of that component.
     * @param vector The {@linkplain Vector} to create the upper bound vector from.
     * @throws UnsupportedOperationException When an element in the {@link Vector}
     *         is not a {@link Numeric}
     * @return a {@link Vector} with all the elements set to their respective upper bounds
     */
    public static Vector upperBoundVector(Vector vector) {
        Vector.Builder upper = Vector.newBuilder();
        for (Numeric element : vector) {
            upper.addWithin(element.getBounds().getUpperBound(), element.getBounds());
        }
        return upper.build();
    }

    /**
     * Constructs a {@link Vector} from <code>vector</code> Vector with each component's value
     * set to the lower bound of that component.
     * @param vector The {@linkplain Vector} from which to create the lower bound vector.
     * @throws UnsupportedOperationException when an element in the {@link Vector}
     *         is not a {@link Numeric}
     * @return a {@link Vector} with all the elements set to their respective lower bounds
     */
    public static Vector lowerBoundVector(Vector vector) {
        Vector.Builder lower = Vector.newBuilder();
        for (Numeric element : vector) {
            lower.addWithin(element.getBounds().getLowerBound(), element.getBounds());
        }
        return lower.build();
    }

    /**
     * Determine the sum of a list of {@code Vector} instances.
     * @param vectors The {@code Vector} instances to sum.
     * @return The resultant {@code Vector}.
     */
    public static Vector sumOf(Vector... vectors) {
        Vector result = null;//vectors[0].getClone();

        for (Vector vector : vectors) {
            if (result == null) {
                result = vector;
                continue;
            }
            result = result.plus(vector);
        }

        return result;
    }

    public static <T extends Number> Vector transform(Vector vector, Function<Numeric, T> function) {
        Vector.Builder builder = Vector.newBuilder();
        for (Numeric n : vector) {
            builder.addWithin(function.apply(n).doubleValue(), n.getBounds()); //??
        }
        return builder.build();
    }
}
