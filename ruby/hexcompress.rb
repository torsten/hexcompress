module Hexcompress

  def self.decompress(bytes)
    bytes.bytes.map do |byte|
      if byte != 0 && (byte < 33 || byte > 126)
        "%02x" % byte
      else
        byte.chr
      end
    end.join
  end

end
