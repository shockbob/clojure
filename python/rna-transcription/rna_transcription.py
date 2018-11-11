def to_rna(dna_strand):
    trans = {"A":"U", "T":"A","C":"G","G":"C"}
    result = ""
    for ch in dna_strand:
        result = result + trans[ch]
    return result
