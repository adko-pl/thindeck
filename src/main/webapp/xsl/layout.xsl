<?xml version="1.0"?>
<!--
 * Copyright (c) 2015, Thindeck.com
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

 * @author Paul Polishchuk (ppol@ua.fm)
 * @version $Id$
 -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns="http://www.w3.org/1999/xhtml" version="1.0">
    <xsl:template match="/page">
        <xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text>
        <html>
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <style type="text/css">
                    * {
                        font-family: monospace;
                        font-size: 20px;
                    }
                    body {
                        padding: 2em;
                    }
                </style>
                <xsl:apply-templates select="." mode="head"/>
            </head>
            <body>
                <section itemscope="" itemtype="http://schema.org/WebApplication">
                    <nav role="navigation" class="menu">
                        <xsl:if test="not(identity)">
                            <a href="{links/link[@rel='rexsl:github']/@href}" title="login via Github">
                                <xsl:text>login</xsl:text>
                            </a>
                            <xsl:text>|</xsl:text>
                        </xsl:if>
                        <a href="{links/link[@rel='home']/@href}">
                            <xsl:text>home</xsl:text>
                        </a>
                        <xsl:text>|</xsl:text>
                        <a href="http://doc.thindeck.com">
                            <xsl:text>documentation</xsl:text>
                        </a>
                        <xsl:apply-templates select="identity"/>
                    </nav>
                </section>
                <a href="https://github.com/yegor256/thindeck">
                    <img src="//img.jcabi.com/fork-me.svg"
                        style="position: absolute;right:0;top:0;width:128px;height:128px;"
                        alt="fork me in github"/>
                </a>
                <p style="color:red;">
                    <xsl:text>pre-alpha testing version, be careful</xsl:text>
                </p>
                <div class="content" role="main">
                    <xsl:apply-templates select="." mode="body"/>
                </div>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="identity">
        <xsl:text>|</xsl:text>
        <a href="{/page/links/link[@rel='account']/@href}">
            <xsl:value-of select="name"/>
        </a>
        <xsl:text>|</xsl:text>
        <a title="log out" href="{/page/links/link[@rel='rexsl:logout']/@href}">
            <xsl:text>logout</xsl:text>
        </a>
    </xsl:template>
</xsl:stylesheet>
