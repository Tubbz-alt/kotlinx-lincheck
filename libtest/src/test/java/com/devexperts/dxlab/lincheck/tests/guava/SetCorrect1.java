/*
 *  Lincheck - Linearizability checker
 *  Copyright (C) 2015 Devexperts LLC
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.devexperts.dxlab.lincheck.tests.guava;

/*
 * #%L
 * libtest
 * %%
 * Copyright (C) 2015 - 2018 Devexperts, LLC
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import com.devexperts.dxlab.lincheck.LinChecker;
import com.devexperts.dxlab.lincheck.annotations.Operation;
import com.devexperts.dxlab.lincheck.annotations.Param;
import com.devexperts.dxlab.lincheck.paramgen.IntGen;
import com.devexperts.dxlab.lincheck.strategy.stress.StressCTest;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@StressCTest
@Param(name = "key", gen = IntGen.class)
public class SetCorrect1 {
    private Set<Integer> q = Collections.newSetFromMap(new ConcurrentHashMap<Integer, Boolean>());;

    @Operation(params = {"key"})
    public boolean add(Integer params) {
        return q.add(params);
    }

    @Operation(params = {"key"})
    public boolean remove(Integer params) {
        return q.remove(params);
    }

    @Test
    public void test() throws Exception {
        LinChecker.check(SetCorrect1.class);
    }
}
