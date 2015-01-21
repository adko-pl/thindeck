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
package com.thindeck.life;

import com.thindeck.api.Base;
import com.thindeck.api.mock.MkBase;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Lifecycle}.
 *
 * @author Krzysztof Krason (Krzysztof.Krason@gmail.com)
 * @version $Id$
 */
public final class LifecycleTest {

    /**
     * Lifecycle will add base attribute with correct class.
     */
    @Test
    public void initializedBaseAttribute() {
        final ServletContextEvent event = Mockito
            .mock(ServletContextEvent.class);
        final ServletContext context = Mockito.mock(ServletContext.class);
        Mockito.when(event.getServletContext()).thenReturn(context);
        final Lifecycle lifecycle = new Lifecycle(false);
        lifecycle.contextInitialized(event);
        try {
            Mockito.verify(context).setAttribute(
                Mockito.eq(Base.class.getName()),
                Mockito.argThat(Matchers.instanceOf(Base.class))
            );
        } finally {
            lifecycle.contextDestroyed(event);
        }
    }

    /**
     * RoutineTxn can increment transactions.
     * @throws IOException In case of error.
     */
    @Test
    public void incrementsTransactionCount() throws IOException {
        final Base base = new MkBase();
        final RoutineTxns routine = new RoutineTxns(base);
        routine.close();
        routine.run();
    }
}
