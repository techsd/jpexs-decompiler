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
package com.jpexs.decompiler.flash.abc.avm2.instructions.types;

import com.jpexs.decompiler.flash.abc.ABC;
import com.jpexs.decompiler.flash.abc.avm2.ConstantPool;
import com.jpexs.decompiler.flash.abc.avm2.LocalDataArea;
import com.jpexs.decompiler.flash.abc.avm2.instructions.AVM2Instruction;
import com.jpexs.decompiler.flash.abc.avm2.instructions.InstructionDefinition;
import com.jpexs.decompiler.flash.abc.avm2.treemodel.ConvertTreeItem;
import com.jpexs.decompiler.flash.abc.avm2.treemodel.TreeItem;
import com.jpexs.decompiler.flash.abc.types.MethodInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ConvertDIns extends InstructionDefinition implements CoerceOrConvertTypeIns {

   public ConvertDIns() {
      super(0x75, "convert_d", new int[]{});
   }

   @Override
   public void execute(LocalDataArea lda, ConstantPool constants, List arguments) {
      Object value = lda.operandStack.pop();
      double ret;
      if (value == null) {
         ret = 0;
      } else if (value instanceof Boolean) {
         if (((Boolean) value).booleanValue()) {
            ret = 1;
         } else {
            ret = 0;
         }
      } else if (value instanceof Long) {
         ret = ((Long) value).longValue();
      } else if (value instanceof Double) {
         ret = ((Double) value).doubleValue();
      } else if (value instanceof String) {
         ret = Double.parseDouble((String) value);
      } else {
         ret = 1; //must call toPrimitive
      }
      lda.operandStack.push(new Double(ret));
   }

   @Override
   public void translate(boolean isStatic, int classIndex, java.util.HashMap<Integer, TreeItem> localRegs, Stack<TreeItem> stack, java.util.Stack<TreeItem> scopeStack, ConstantPool constants, AVM2Instruction ins, MethodInfo[] method_info, List<TreeItem> output, com.jpexs.decompiler.flash.abc.types.MethodBody body, com.jpexs.decompiler.flash.abc.ABC abc, HashMap<Integer, String> localRegNames, List<String> fullyQualifiedNames) {
      stack.push(new ConvertTreeItem(ins, (TreeItem) stack.pop(), getTargetType(constants, ins, fullyQualifiedNames)));
   }

   @Override
   public int getStackDelta(AVM2Instruction ins, ABC abc) {
      return -1 + 1;
   }

   public String getTargetType(ConstantPool constants, AVM2Instruction ins, List<String> fullyQualifiedNames) {
      return "Number";
   }
}