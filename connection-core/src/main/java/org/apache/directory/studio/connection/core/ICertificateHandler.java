/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */

package org.apache.directory.studio.connection.core;


import java.security.cert.X509Certificate;
import java.util.List;


/**
 * Callback interface to ask for the trust level of a certificate from a 
 * higher-level layer (from the UI plugin).
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public interface ICertificateHandler
{

    /**
     * The trust level of a certificate.
     */
    enum TrustLevel
    {
        /** Don't trust a certificate. */
        Not,

        /** Trust a certificate within the current session. */
        Session,

        /** Trust a certificate permanently. */
        Permanent;
    }

    /**
     * The cause of certificate verification failure.
     */
    enum FailCause
    {
        /** No valid certification path, i.e. unknown issuer.  */
        NoValidCertificationPath,

        /** Certificate is not valid yet */
        CertificateNotYetValid,

        /** Certificate is expired */
        CertificateExpired,

        /** Certificate is self signed */
        SelfSignedCertificate,

        /** The host name of the server doesn't match the host name in certificate */
        HostnameVerificationFailed
    }


    /**
     * Verifies the trust level of the given certificate chain.
     * 
     * @param certChain the certificate chain
     * @param failCauses the causes of failed certificate validation
     * 
     * @return the trust level
     */
    TrustLevel verifyTrustLevel( String host, X509Certificate[] certChain,
        List<ICertificateHandler.FailCause> failCauses );

}
