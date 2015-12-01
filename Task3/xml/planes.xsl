<?xml version="1.0"?>
<xsl:stylesheet version="1.0" 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:pln="http://www.andrew.com/planes">
  <xsl:template match="/">
    <html>
    <head>
    <title>List of all Planes</title>
      <xsl:apply-templates/>
    </head>
    </html>
  </xsl:template>
  <xsl:template match="pln:planes">
    <body>
    <table border="1" cellpadding="5px">
	<tr><td colspan="12" style="text-align:center;"><b>List of all Planes</b></td></tr>
	<tr bgcolor="#d4d6e0">
	<td><b>Model</b></td>
	<td><b>ModelId</b></td>
	<td><b>Origin</b></td>
	<td><b>Price</b></td>
	<td><b>Chars/Type</b></td>
	<td><b>Chars/Places</b></td>
	<td><b>Chars/Guns</b></td>
	<td><b>Chars/Guns/Present</b></td>
	<td><b>Chars/Radar</b></td>
	<td><b>Params/Length</b></td>
	<td><b>Params/Width</b></td>
	<td><b>Params/Height</b></td>
	</tr>
	<xsl:apply-templates/>
    </table>
    </body>
  </xsl:template>
  <xsl:template match="pln:plane">

	<tr>
	<td><xsl:value-of select="pln:model"/></td>
	<td><xsl:value-of select="pln:model/@modelId"/></td>
	<td><xsl:value-of select="pln:origin"/></td>
	<td><xsl:value-of select="pln:price"/></td>
	<td><xsl:value-of select="pln:chars/pln:type"/></td>
	<td><xsl:value-of select="pln:chars/pln:places"/></td>
	<td><xsl:value-of select="pln:chars/pln:guns"/></td>
	<td><xsl:value-of select="pln:chars/pln:guns/@present"/></td>
	<td><xsl:value-of select="pln:chars/pln:radar"/></td>
	<td><xsl:value-of select="pln:params/pln:length"/></td>
	<td><xsl:value-of select="pln:params/pln:width"/></td>
	<td><xsl:value-of select="pln:params/pln:height"/></td>
	</tr>

  </xsl:template>
</xsl:stylesheet>


