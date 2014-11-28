require('should');

var hexcompress = require('../lib/hexcompress');

describe('hexcompress.decompress', function() {

  it('keeps ascii', function() {
    hexcompress.decompress(new Buffer("asd")).should.equal("asd");
  });

  it('decodes hex', function() {
    hexcompress.decompress(new Buffer([0x61, 0x73, 0xdf])).should.equal("asdf");
  });

  it('decodes capital ascii', function() {
    hexcompress.decompress(new Buffer("asdF")).should.equal("asdF");
  });

  it('decodes single hex', function() {
    hexcompress.decompress(new Buffer([0x1f])).should.equal("1f");
  });

  it('decodes single hex with follow-up', function() {
    hexcompress.decompress(new Buffer([0x1f, 0x41])).should.equal("1fA");
  });

  it('decodes single hex with prefix', function() {
    hexcompress.decompress(new Buffer([0x7a, 0x1f])).should.equal("z1f");
  });
  
  it('keeps 00', function() {
    hexcompress.decompress(new Buffer("00")).should.equal("00");
  });

  it('keeps null byte', function() {
    hexcompress.decompress(new Buffer([0x00])).should.equal("\u0000");
  });

  it('keeps 41', function() {
    hexcompress.decompress(new Buffer("41")).should.equal("41");
  });

  it('unpacks trailing zeroes', function() {
    hexcompress.decompress(new Buffer([0x01, 0x02])).should.equal("0102");
  });
});
