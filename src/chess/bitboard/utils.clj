(ns chess.bitboard.utils)

(defn rank-bb
  "Set all bits to 1 along the specified rank"
  [rank]
  (apply bit-or
    (for [file [1 2 3 4 5 6 7 8]]
      (bit-flip 0 (- (* 8 rank) file)))))

(defn file-bb 
  "Set all bits to 1 along the specified rank"
  [file]
  (apply bit-or
    (for [rank [1 2 3 4 5 6 7 8]]
      (bit-flip 0 (- (* 8 rank) file)))))

; 1s all around the edge of the board
(def edges -35604928818740737) 

(defn bitscan+
  "
  Bitscan forward (returns index of LSB)
  MSB <-                       -> LSB
  H1H2H3H4H5H6H7H8...A1A2A3A4A5A6A7A8 
  "
  [bb]
  (Long/numberOfTrailingZeros (Long/lowestOneBit bb)))

(defn bitscan-
  "
  Bitscan backward (returns index of MSB)
  MSB <-                       -> LSB
  H1H2H3H4H5H6H7H8...A1A2A3A4A5A6A7A8 
  "
  [bb]
  (Long/numberOfTrailingZeros (Long/highestOneBit bb)))

(defn bb->string
  "Convert a bitboard (Long) into a string"
  [bb]
  (java.lang.Long/toBinaryString bb))

(defn preceding-zeros
  "Returns the preceding 0s to prepend to a bitboard string so that it has length 64"
  [bbstring]
  (apply str (take (- 64 (count bbstring)) (repeat \0))))

(defn print-bb
  "
  Prints a bitboard in human readable form
  
  => (print-bb 0)   => (print-bb (bit-shift-left 1 45))
  00000000          00000000
  00000000          00000000
  00000000          00100000
  00000000          00000000
  00000000          00000000
  00000000          00000000
  00000000          00000000
  00000000          00000000
  "
  [bb]
  (let [bbstring (bb->string bb)
        zeros (preceding-zeros bbstring)
        fullstring (str zeros bbstring)]
    (doseq [lst (map #(apply str %) (partition 8 fullstring))]
      (println lst))))

(defn index->rank
  "Returns the rank from the given index"
  [index]
  (inc (quot index 8)))

(defn index->file
  "Returns the file from the given index"
  [index]
  (- 8 (mod index 8)))

(defn get-rank
  "Returns the given rank from the given bitboard"
  [bb rank]
  (let [bbstring (bb->string bb)
        zeros (preceding-zeros bbstring)
        fullstring (str zeros bbstring)
        rows (vec (partition 8 fullstring))]
    (rows (- 7 (dec rank)))))

(defn get-file
  "Returns the given file from the given bitboard"
  [bb file]
  (let [bbstring (bb->string bb)
        zeros (preceding-zeros bbstring)
        fullstring (str zeros bbstring)
        rows (partition 8 fullstring)]
    ((vec
      (partition 8 (apply str (apply interleave rows))))
      (dec file))))

(defn rook-occupancy-mask
  "Occupancy mask for a rook on the given square, including edges"
  [index]
  (bit-xor
    (file-bb (index->file index))
    (rank-bb (index->rank index))))

(defn rook-relevant-occupancy-mask
  "Relevant occupancy mask for a rook on the given square"
  [index]
  (bit-xor
    (rook-occupancy-mask index)
    (bit-and
      edges
      (rook-occupancy-mask index))))
