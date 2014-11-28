"use strict";

function decompress(buffer) {
  var result = new Buffer(buffer.length * 2);
  var r = 0;

  for (var i=0; i < buffer.length; i++) {
    var byte = buffer[i];
    // 33: !, 126: ~
    if (byte !== 0 && (byte < 33 || byte > 126)) {
      var hex = byte.toString(16);
      if (byte < 16) {
        hex = "0" + hex;
      }
      result[r] = hex.charCodeAt(0);
      result[r + 1] = hex.charCodeAt(1);
      r += 2;
    }
    else {
      result[r] = byte;
      r++;
    }
  }

  return result.toString("ascii", 0, r);
}

module.exports = {
  decompress: decompress
};
