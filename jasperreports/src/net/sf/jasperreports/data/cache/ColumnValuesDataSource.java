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
package net.sf.jasperreports.data.cache;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRRewindableDataSource;
import net.sf.jasperreports.engine.data.IndexedDataSource;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id$
 */
public class ColumnValuesDataSource implements JRRewindableDataSource, IndexedDataSource
{

	private int size;
	private int iteratorIndex;
	private Map<String, ColumnValuesIterator> iterators;
	
	public ColumnValuesDataSource(String[] fieldNames, int size, ColumnValues[] values)
	{
		if (fieldNames == null || values == null || fieldNames.length != values.length)
		{
			throw new IllegalArgumentException();
		}
		
		iterators = new LinkedHashMap<String, ColumnValuesIterator>();
		
		this.size = size;
		for (int i = 0; i < fieldNames.length; i++)
		{
			if (size != values[i].size())
			{
				throw new IllegalArgumentException();
			}
			
			iterators.put(fieldNames[i], values[i].iterator());
		}
		
		iteratorIndex = 0;
	}
	
	public boolean next() throws JRException
	{
		if (iteratorIndex >= size)
		{
			return false;
		}
		
		++iteratorIndex;
		for (ColumnValuesIterator iterator : iterators.values())
		{
			iterator.next();
		}
		
		return true;
	}

	public Object getFieldValue(JRField field) throws JRException
	{
		ColumnValuesIterator iterator = iterators.get(field.getName());
		if (iterator == null)
		{
			throw new JRException("No such field " +  field.getName());
		}
		
		return iterator.get();
	}

	public void moveFirst()
	{
		iteratorIndex = 0;
		for (ColumnValuesIterator iterator : iterators.values())
		{
			iterator.moveFirst();
		}
	}

	public int getRecordIndex()
	{
		return iteratorIndex - 1;
	}

}