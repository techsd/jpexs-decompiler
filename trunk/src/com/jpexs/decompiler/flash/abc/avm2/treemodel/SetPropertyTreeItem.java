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
package com.jpexs.decompiler.flash.abc.avm2.treemodel;

import com.jpexs.decompiler.flash.abc.avm2.ConstantPool;
import com.jpexs.decompiler.flash.abc.avm2.instructions.AVM2Instruction;
import com.jpexs.decompiler.flash.abc.avm2.treemodel.clauses.AssignmentTreeItem;
import java.util.HashMap;
import java.util.List;

public class SetPropertyTreeItem extends TreeItem implements SetTypeTreeItem, AssignmentTreeItem {

   public TreeItem object;
   public FullMultinameTreeItem propertyName;
   public TreeItem value;

   public SetPropertyTreeItem(AVM2Instruction instruction, TreeItem object, FullMultinameTreeItem propertyName, TreeItem value) {
      super(instruction, PRECEDENCE_ASSIGMENT);
      this.object = object;
      this.propertyName = propertyName;
      this.value = value;
   }

   @Override
   public String toString(ConstantPool constants, HashMap<Integer, String> localRegNames, List<String> fullyQualifiedNames) {
      return formatProperty(constants, object, propertyName, localRegNames, fullyQualifiedNames) + hilight("=") + value.toString(constants, localRegNames, fullyQualifiedNames);
   }

   public TreeItem getObject() {
      return new GetPropertyTreeItem(instruction, object, propertyName);
   }

   public TreeItem getValue() {
      return value;
   }
}