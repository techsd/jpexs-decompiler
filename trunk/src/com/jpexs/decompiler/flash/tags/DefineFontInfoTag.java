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
package com.jpexs.decompiler.flash.tags;

import com.jpexs.decompiler.flash.SWFInputStream;
import com.jpexs.decompiler.flash.SWFOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author JPEXS
 */
public class DefineFontInfoTag extends Tag {

   public int fontId;
   public String fontName;
   public boolean fontFlagsSmallText;
   public boolean fontFlagsShiftJIS;
   public boolean fontFlagsANSI;
   public boolean fontFlagsItalic;
   public boolean fontFlagsBold;
   public boolean fontFlagsWideCodes;
   public List<Integer> codeTable;

   /**
    * Gets data bytes
    *
    * @param version SWF version
    * @return Bytes of data
    */
   @Override
   public byte[] getData(int version) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      OutputStream os = baos;
      SWFOutputStream sos = new SWFOutputStream(os, version);
      try {
         sos.writeUI16(fontId);
         sos.writeUI8(fontName.getBytes().length);
         sos.write(fontName.getBytes());
         sos.writeUB(2, 0); //reserved
         sos.writeUB(1, fontFlagsSmallText ? 1 : 0);
         sos.writeUB(1, fontFlagsShiftJIS ? 1 : 0);
         sos.writeUB(1, fontFlagsANSI ? 1 : 0);
         sos.writeUB(1, fontFlagsItalic ? 1 : 0);
         sos.writeUB(1, fontFlagsBold ? 1 : 0);
         sos.writeUB(1, fontFlagsWideCodes ? 1 : 0);
         for (int code : codeTable) {
            if (fontFlagsWideCodes) {
               sos.writeUI16(code);
            } else {
               sos.writeUI8(code);
            }
         }
      } catch (IOException e) {
      }
      return baos.toByteArray();
   }

   /**
    * Constructor
    *
    * @param data Data bytes
    * @param version SWF version
    * @throws IOException
    */
   public DefineFontInfoTag(byte data[], int version, long pos) throws IOException {
      super(13, "DefineFontInfo", data, pos);
      SWFInputStream sis = new SWFInputStream(new ByteArrayInputStream(data), version);
      fontId = sis.readUI16();
      int fontNameLen = sis.readUI8();
      fontName = new String(sis.readBytes(fontNameLen));
      sis.readUB(2); //reserved
      fontFlagsSmallText = sis.readUB(1) == 1;
      fontFlagsShiftJIS = sis.readUB(1) == 1;
      fontFlagsANSI = sis.readUB(1) == 1;
      fontFlagsItalic = sis.readUB(1) == 1;
      fontFlagsBold = sis.readUB(1) == 1;
      fontFlagsWideCodes = sis.readUB(1) == 1;
      codeTable = new ArrayList<Integer>();
      do {
         if (fontFlagsWideCodes) {
            codeTable.add(sis.readUI16());
         } else {
            codeTable.add(sis.readUI8());
         }
      } while (sis.available() > 0);
   }
}