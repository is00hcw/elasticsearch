/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.common.jna;

import com.sun.jna.Native;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;


/**
 *
 */
public class CLibrary {

    private static ESLogger logger = Loggers.getLogger(CLibrary.class);

    public static final int MCL_CURRENT = 1;
    public static final int MCL_FUTURE = 2;

    public static final int ENOMEM = 12;

    static {
        try {
            Native.register("c");
        } catch (NoClassDefFoundError e) {
            logger.warn("jna not found. native methods (mlockall) will be disabled.");
        } catch (UnsatisfiedLinkError e) {
            logger.debug("unable to link C library. native methods (mlockall) will be disabled.");
        }
    }

    public static native int mlockall(int flags);

    public static native int munlockall();

    private CLibrary() {
    }
}
