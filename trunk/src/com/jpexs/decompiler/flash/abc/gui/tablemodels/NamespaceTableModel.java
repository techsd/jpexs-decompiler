/*
 *  Copyright (C) 2010-2013 JPEXS
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
package com.jpexs.decompiler.flash.abc.gui.tablemodels;

import com.jpexs.decompiler.flash.abc.ABC;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class NamespaceTableModel implements TableModel {

   private ABC abc;
   private static final String columnNames[] = new String[]{"Index", "Kind", "Name"};
   private static final Class classes[] = new Class[]{Long.class, String.class, String.class};

   public NamespaceTableModel(ABC abc) {
      this.abc = abc;
   }

   /**
    * Returns the number of rows in the model. A
    * <code>JTable</code> uses this method to determine how many rows it should
    * display. This method should be quick, as it is called frequently during
    * rendering.
    *
    * @return the number of rows in the model
    * @see #getColumnCount
    */
   public int getRowCount() {
      if (abc == null) {
         return 0;
      }
      return abc.constants.constant_namespace.length;
   }

   /**
    * Returns the number of columns in the model. A
    * <code>JTable</code> uses this method to determine how many columns it
    * should create and display by default.
    *
    * @return the number of columns in the model
    * @see #getRowCount
    */
   public int getColumnCount() {
      return 3;
   }

   /**
    * Returns the name of the column at
    * <code>columnIndex</code>. This is used to initialize the table's column
    * header name. Note: this name does not need to be unique; two columns in a
    * table can have the same name.
    *
    * @param columnIndex the index of the column
    * @return the name of the column
    */
   public String getColumnName(int columnIndex) {
      return columnNames[columnIndex];
   }

   /**
    * Returns the most specific superclass for all the cell values in the
    * column. This is used by the
    * <code>JTable</code> to set up a default renderer and editor for the
    * column.
    *
    * @param columnIndex the index of the column
    * @return the common ancestor class of the object values in the model.
    */
   public Class<?> getColumnClass(int columnIndex) {
      return classes[columnIndex];
   }

   /**
    * Returns true if the cell at
    * <code>rowIndex</code> and
    * <code>columnIndex</code> is editable. Otherwise,
    * <code>setValueAt</code> on the cell will not change the value of that
    * cell.
    *
    * @param rowIndex the row whose value to be queried
    * @param columnIndex the column whose value to be queried
    * @return true if the cell is editable
    * @see #setValueAt
    */
   public boolean isCellEditable(int rowIndex, int columnIndex) {
      return false;
   }

   /**
    * Returns the value for the cell at
    * <code>columnIndex</code> and
    * <code>rowIndex</code>.
    *
    * @param rowIndex the row whose value is to be queried
    * @param columnIndex the column whose value is to be queried
    * @return the value Object at the specified cell
    */
   public Object getValueAt(int rowIndex, int columnIndex) {
      switch (columnIndex) {
         case 0:
            return rowIndex;
         case 1:
            if (rowIndex == 0) {
               return "-";
            }
            return abc.constants.constant_namespace[rowIndex].getKindStr();
         case 2:
            if (rowIndex == 0) {
               return "-";
            }
            return abc.constants.constant_namespace[rowIndex].getName(abc.constants);
         default:
            return null;
      }
   }

   /**
    * Sets the value in the cell at
    * <code>columnIndex</code> and
    * <code>rowIndex</code> to
    * <code>aValue</code>.
    *
    * @param aValue the new value
    * @param rowIndex the row whose value is to be changed
    * @param columnIndex the column whose value is to be changed
    * @see #getValueAt
    * @see #isCellEditable
    */
   public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
   }

   /**
    * Adds a listener to the list that is notified each time a change to the
    * data model occurs.
    *
    * @param l the TableModelListener
    */
   public void addTableModelListener(TableModelListener l) {
   }

   /**
    * Removes a listener from the list that is notified each time a change to
    * the data model occurs.
    *
    * @param l the TableModelListener
    */
   public void removeTableModelListener(TableModelListener l) {
   }
}