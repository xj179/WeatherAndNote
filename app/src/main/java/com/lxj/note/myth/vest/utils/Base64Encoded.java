package com.lxj.note.myth.vest.utils;



/* Base32
*
* $Id: Base32.java 1828 2004-04-15 19:04:01Z stack-sf $
*
* Created on Jan 21, 2004
*
* Copyright (C) 2004 Internet Archive.
*
* This file is part of the Heritrix web crawler (crawler.archive.org).
*
* Heritrix is free software; you can redistribute it and/or modify
* it under the terms of the GNU Lesser Public License as published by
* the Free Software Foundation; either version 2.1 of the License, or
* any later version.
*
* Heritrix is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser Public License for more details.
*
* You should have received a copy of the GNU Lesser Public License
* along with Heritrix; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

import android.text.TextUtils;
import android.util.Base64;

/**
 * Base32 - encodes and decodes RFC3548 Base32
 * (see http://www.faqs.org/rfcs/rfc3548.html )
 *
 * Imported public-domain code of Bitzi.
 *
 * @author Robert Kaye
 * @author Gordon Mohr
 */
public class Base64Encoded {
    /**
     * Base64解码
     *
     * @param base64Id
     * @return
     */

    public static String getUidFromBase64(String base64Id) {
        String result = "";
        if (!TextUtils.isEmpty(base64Id)) {
            if (!TextUtils.isEmpty(base64Id)) {
                result = new String(Base64.decode(base64Id.getBytes(), Base64.DEFAULT));
            }
        }
        return result;
    }
}
