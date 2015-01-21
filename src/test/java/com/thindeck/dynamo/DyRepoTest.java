/**
 * Copyright (c) 2014-2015, Thindeck.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the thindeck.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.thindeck.dynamo;

import com.jcabi.dynamo.Attributes;
import com.jcabi.dynamo.Region;
import com.jcabi.dynamo.Table;
import com.jcabi.dynamo.mock.H2Data;
import com.jcabi.dynamo.mock.MkRegion;
import java.io.IOException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link DyRepo}.
 *
 * @author Krzysztof Krason (Krzysztof.Krason@gmail.com)
 * @version $Id$
 */
public final class DyRepoTest {
    /**
     * DyRepo can create repo with provided name.
     * @throws IOException In case of error.
     */
    @Test
    public void createsRepoWithName() throws IOException {
        final String name = "some name";
        final Region region = new MkRegion(
            new H2Data().with(
                DyRepo.TBL,
                new String[] {DyRepo.ATTR_NAME},
                new String[] {DyRepo.ATTR_UPDATED}
            )
        );
        region.table(DyRepo.TBL).put(
            new Attributes()
                .with(DyRepo.ATTR_NAME, name)
                .with(DyRepo.ATTR_UPDATED, System.currentTimeMillis())
        );
        MatcherAssert.assertThat(
            new DyRepo(region.table(DyRepo.TBL).frame().iterator().next())
                .name(),
            Matchers.equalTo(name)
        );
    }

    /**
     * DyRepo can get {@code Tasks}.
     * @throws IOException In case of error.
     */
    @Test
    public void getTasks() throws IOException {
        final Region region = new MkRegion(
            new H2Data().with(
                DyRepo.TBL,
                new String[] {DyRepo.ATTR_NAME},
                new String[] {DyRepo.ATTR_UPDATED}
            )
        );
        region.table(DyRepo.TBL).put(
            new Attributes()
                .with(DyRepo.ATTR_NAME, "name")
                .with(DyRepo.ATTR_UPDATED, System.currentTimeMillis())
        );
        MatcherAssert.assertThat(
            new DyRepo(region.table(DyRepo.TBL).frame().iterator().next())
                .tasks(),
            Matchers.notNullValue()
        );
    }

    /**
     * {@link DyRepo#memo}.
     * @throws IOException In case of error.
     */
    @Test
    public void memo() throws IOException {
        final Region region = new MkRegion(
            new H2Data().with(
                    DyRepo.TBL,
                new String[] {DyRepo.ATTR_MEMO},
                new String[] {DyRepo.ATTR_UPDATED}
            )
        );
        final Table table = region.table(DyRepo.TBL);
        table.put(
            new Attributes()
                .with(DyRepo.ATTR_MEMO, "<memo></memo>")
                .with(DyRepo.ATTR_UPDATED, System.currentTimeMillis())
        );
        MatcherAssert.assertThat(
            new DyRepo(table.frame().iterator().next()).memo(),
            Matchers.instanceOf(DyMemo.class)
        );
    }
}
