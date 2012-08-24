/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.components.headertoolbar;

import java.awt.Color;

import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.style.StyleProvider;
import net.sf.jasperreports.engine.style.StyleProviderContext;
import net.sf.jasperreports.engine.util.JRColorUtil;

/**
 * 
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: HeaderToolbarParameterContributor.java 5349 2012-05-08 14:25:05Z teodord $
 */
public class HeaderToolbarConditionalStyleProvider implements StyleProvider
{
	private final StyleProviderContext context;
	
	public HeaderToolbarConditionalStyleProvider(StyleProviderContext context)
	{
		this.context = context;
	}

	@Override
	public JRStyle getStyle(byte evaluation) 
	{
		if (context.getElement().getPropertiesMap() != null)
		{
			String backcolor = context.getElement().getPropertiesMap().getProperty("net.sf.jasperreports.backcolor");
			if (backcolor != null)
			{
				JRStyle style = new JRBaseStyle();
				style.setBackcolor(JRColorUtil.getColor(backcolor, Color.black));
				return style;
			}
		}
		return null;
	}

	@Override
	public String[] getFields() 
	{
		return null;
	}

	@Override
	public String[] getVariables() 
	{
		return null;
	}

}