package org.apache.xml.serialize;

import java.io.*;

public class CachingWriter extends Writer implements CharacterCachingWriter { 

    Writer out;
    StringWriter cache;
    boolean caching;

    public CachingWriter(Writer out) {
        cache=null; caching=false;
        this.out=out;
    }

    public boolean startCaching() {
        if(caching) return false;
        caching=true;
        cache=new StringWriter();
        return true;
    }

    public String getCache(String encoding) throws IOException {
        cache.flush();
        return cache.toString();
    }

    public boolean stopCaching() {
        if(!caching) return false;
        caching=false; return true;
    }

    public void close() throws IOException {
        out.close();
    }
    
    public void flush() throws IOException {
        out.flush();
    }

    public void write(char[] c) throws IOException {
        out.write(c);
        if(caching) cache.write(c);
    }

    public void write(char[] c, int off, int len) throws IOException {
        out.write(c,off,len);
        if(caching) cache.write(c,off,len);
    }

    public void write(int c) throws IOException {
        out.write(c);
        if(caching) cache.write(c);
    }

    public void write(String str) throws IOException {
        out.write(str);
        if(caching) cache.write(str);
    }

    public void write(String str, int off, int len) throws IOException {
        out.write(str,off,len);
        if(caching) cache.write(str,off,len);
    }
}
