<?xml version="1.0"?>
<!--
 * Copyright (c) 2014, Thindeck.com
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
 *
 * @author Yegor Bugayenko (yegor@tpc2.com)
 * @version $Id$
 -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns="http://www.w3.org/1999/xhtml" version="1.0">
    <xsl:output method="xml" omit-xml-declaration="yes"/>
    <xsl:include href="/xsl/layout.xsl" />
    <xsl:template match="page" mode="body">
        <p><xsl:value-of select="repo/name"/></p>
        <form action="{links/link[@rel='add']/@href}" method="post">
            <input name="cmd" size="65" placeholder="start a task..."/>
            <button type="submit">Start</button>
        </form>
        <xsl:if test="not(tasks/task)">
            <p>No tasks in this repo yet.</p>
        </xsl:if>
        <xsl:if test="tasks/task">
            <p>Tasks:</p>
            <ul>
                <xsl:apply-templates select="tasks/task"/>
            </ul>
        </xsl:if>
    </xsl:template>
    <xsl:template match="task">
        <li>
            <xsl:value-of select="number"/>
            <xsl:text>:</xsl:text>
            <xsl:value-of select="command"/>
            <xsl:text> </xsl:text>
            <a href="{links/link[@rel='log']/@href}">
                <xsl:text>log</xsl:text>
            </a>
        </li>
    </xsl:template>
</xsl:stylesheet>
