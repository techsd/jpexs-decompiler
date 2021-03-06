/*
 *  Copyright (C) 2010-2014 JPEXS, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */
package com.jpexs.decompiler.flash.tags.base;

import java.awt.Font;
import java.util.Map;

/**
 *
 * @author JPEXS
 */
public class MissingCharacterHandler {

    public boolean handle(FontTag font, char character) {
        String fontName = font.getFontNameIntag();
        if (!FontTag.installedFontsByFamily.containsKey(fontName)) {
            return false;
        }
        Map<String, Font> faces = FontTag.installedFontsByFamily.get(fontName);

        Font f = null;
        for (String face : faces.keySet()) {
            Font ff = faces.get(face);
            if (ff.isBold() == font.isBold() && ff.isItalic() == font.isItalic()) {
                f = ff;
                break;
            }
        }
        if (f == null) {
            f = faces.get(faces.keySet().iterator().next());
        }
        if (!f.canDisplay(character)) {
            return false;
        }
        font.addCharacter(character, f);
        return true;
    }
}
