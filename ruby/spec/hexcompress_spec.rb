require_relative '../hexcompress.rb'

describe 'Hexcompress.decompress' do

  it 'keeps ascii' do
    Hexcompress.decompress("asd").should eq("asd")
  end

  it 'decodes hex' do
    Hexcompress.decompress("\x61\x73\xdf".encode("ASCII-8BIT")).should eq("asdf")
  end

  it 'decodes capital ascii' do
    Hexcompress.decompress("asdF").should eq("asdF")
  end

  it 'decodes single hex' do
    Hexcompress.decompress("\x1f".encode("ASCII-8BIT")).should eq("1f")
  end

  it 'decodes single hex with follow-up' do
    Hexcompress.decompress("\x1f\x41".encode("ASCII-8BIT")).should eq("1fA")
  end

  it 'decodes single hex with prefix' do
    Hexcompress.decompress("\x7a\x1f".encode("ASCII-8BIT")).should eq("z1f")
  end
  
  it 'keeps 00' do
    Hexcompress.decompress("00").should eq("00")
  end

  it 'keeps null byte' do
    Hexcompress.decompress("\x00".encode("ASCII-8BIT")).should eq("\u0000")
  end

  it 'keeps 41' do
    Hexcompress.decompress("41").should eq("41")
  end

  it 'unpacks trailing zeroes' do
    Hexcompress.decompress("\x01\x02".encode("ASCII-8BIT")).should eq("0102")
  end

end
