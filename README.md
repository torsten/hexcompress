

# Hexcompress

Hexcompress **is not a generic compression algorithm**.

Instead, hexcompress is very good at compressing short-ish (~ 1-500 characters) ASCII **strings that contain many numbers** or hex-encoded bytes.

For example the string `"caffeebabe"` (10&nbsp;bytes) will compress to `"\xCA\xFF\xEE\xBA\xBE"` (5&nbsp;bytes), that's 50% compression!

Mixed strings are encoded like so: `"eat more beef!!1111"` (19&nbsp;bytes) will become `"\xEAt more \xBE\xEF!!\x11\x11"` (14&nbsp;bytes), that's 26% compression.

Since printable ASCII characters are preserved as is, that means **0% worst-case compression** when no hex-numbers are present, at least your data won't get any bigger!

Currently, compression is optional. Uncompressing the data `"1337"` will just decompress to the string `"1337"` which makes it easy to integrate with new code that may or may not compress yet. (This fact may change in the future)


## Libraries

The Clojure version of hexcompress is currently released via [Clojars](https://clojars.org/io.torsten/hexcompress). The latest release is `0.0.3`:

```clojure
[io.torsten/hexcompress "0.0.3"]
```

The Javascript version is in the `javascript` subdirectory.


## Related Work

**smaz**  
https://github.com/antirez/smaz  
Did not perform well in our case because the data contained a lot of high-entropy hexadecimal characters which usually yielded negative compression.

**zlib/deflate**  
We saw about ~15% compression with deflate, hexcompress usually produced ~30% compression for our data.


## License

Copyright © 2014 Torsten Becker.

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
