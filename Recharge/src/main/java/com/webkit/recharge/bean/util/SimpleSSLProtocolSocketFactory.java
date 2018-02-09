package com.webkit.recharge.bean.util;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;

import javax.net.ssl.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Created by HH on 2018/2/1.
 */
public class SimpleSSLProtocolSocketFactory implements SecureProtocolSocketFactory {
    private URL keystoreUrl = null;
    private String keystorePassword = null;
    private URL truststoreUrl = null;
    private String truststorePassword = null;
    private SSLContext sslcontext = null;

    public SimpleSSLProtocolSocketFactory(URL keystoreUrl, String keystorePassword, URL truststoreUrl, String truststorePassword) {
        this.keystoreUrl = keystoreUrl;
        this.keystorePassword = keystorePassword;
        this.truststoreUrl = truststoreUrl;
        this.truststorePassword = truststorePassword;
    }

    private KeyStore cretateKeyStore(URL url, String password)
            throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        if (url == null) {
            throw new IllegalArgumentException(
                    "Keystore url may not be null");
        }
        KeyStore ks = KeyStore.getInstance("jks");
        InputStream is = null;
        try {
            is = url.openStream();
            ks.load(is, (password != null) ? password.toCharArray() : null);
        } finally {
            if (is != null)
                is.close();
        }
        return ks;
    }

    private TrustManagerFactory createTrustManagerFactory(KeyStore ks) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory tmf =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        return tmf;
    }

    private KeyManagerFactory createKeyManagerFactory(KeyStore ks)
            throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, FileNotFoundException, UnrecoverableKeyException {
        KeyManagerFactory kmf =
                KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, (this.keystorePassword != null) ?
                this.keystorePassword.toCharArray() : null);
        return kmf;
    }

    private SSLContext createSSLContext() {
        try {
            KeyManager[] keymanagers = (KeyManager[]) null;
            TrustManager[] trustmanagers = (TrustManager[]) null;
            if (this.keystoreUrl != null) {
                KeyStore keystore = cretateKeyStore(this.keystoreUrl,
                        this.keystorePassword);
                KeyManagerFactory kmfactory = createKeyManagerFactory(keystore);
                keymanagers = kmfactory.getKeyManagers();
            }
            if (this.truststoreUrl != null) {
                KeyStore keystore = cretateKeyStore(this.truststoreUrl,
                        this.truststorePassword);
                TrustManagerFactory tmfactory = createTrustManagerFactory(keystore);
                trustmanagers = tmfactory.getTrustManagers();
            }

            String os = System.getProperty("java.vm.vendor");
            String sslprotocol = "SSL";
            if (os.equalsIgnoreCase("IBM Corporation"))
                sslprotocol = "SSL_TLS";
            else {
                sslprotocol = "TLS";
            }
            SSLContext sslcontext = SSLContext.getInstance(sslprotocol);

            sslcontext.init(keymanagers, trustmanagers, null);
            return sslcontext;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private SSLContext getSSLContext() {
        if (this.sslcontext == null) {
            this.sslcontext = createSSLContext();
        }
        return this.sslcontext;
    }

    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(socket,
                host, port, autoClose);
    }

    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(host, port);
    }

    public Socket createSocket(String host, int port, InetAddress localAddress, int localPort)
            throws IOException, UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(host, port,
                localAddress, localPort);
    }

    public Socket createSocket(String host, int port, InetAddress localAddress, int localPort, HttpConnectionParams params)
            throws IOException, UnknownHostException, ConnectTimeoutException {
        if (params == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        }
        int timeout = params.getConnectionTimeout();
        if (timeout == 0) {
            return getSSLContext().getSocketFactory().createSocket(host, port, localAddress, localPort);
        }
        Socket socket = getSSLContext().getSocketFactory().createSocket();
        SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
        SocketAddress remoteaddr = new InetSocketAddress(host, port);
        socket.bind(localaddr);
        socket.connect(remoteaddr, timeout);
        return socket;
    }
}
