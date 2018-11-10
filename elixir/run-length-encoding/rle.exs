defmodule RunLengthEncoder do
  @doc """
  Generates a string where consecutive elements are represented as a data value and count.
  "AABBBCCCC" => "2A3B4C"
  For this example, assume all input are strings, that are all uppercase letters.
  It should also be able to reconstruct the data into its original form.
  "2A3B4C" => "AABBBCCCC"
  """
  def build_chunk({char,count}) do
     if (count == 1) do
        List.to_string([char])
     else
        Integer.to_string(count)<>List.to_string([char])
     end
  end
        
  @spec encode(String.t()) :: String.t()
  def encode(string) do
      l = Enum.map(Enum.chunk_by(to_charlist(string),&(&1)),&({List.first(&1),Enum.count(&1)}))
      List.to_string(List.flatten(Enum.map(l,&(build_chunk(&1)))))
  end

 def is_num(c) do
    c <= ?9 && c >= ?0
  end

  def do_concat(c,s,e) do
    es = List.to_string([e])
    if (c == "") do
       s<>es
    else
       s<>String.duplicate(es,String.to_integer(c))
    end
 end

  @spec decode(String.t()) :: String.t()
  def decode(string) do
     {_,str} = Enum.reduce(String.to_char_list(string),{"",""}, fn x, {count,acc} ->
              if (is_num(x)) do
                 {count<>List.to_string([x]),acc}
                 else
                 {"",do_concat(count,acc,x)}
                 end
         end)
      str
  end
end
