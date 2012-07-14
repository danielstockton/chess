(ns chess.bitboard.utils)

(defn bb->string
  "Convert a bitboard (Long) into a string"
  [bb]
  (java.lang.Long/toBinaryString bb))

(defn prec0s
  "Adds the preceding 0s to a bitboard string so that it has length 64"
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
        zeros (prec0s bbstring)
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
        zeros (prec0s bbstring)
        fullstring (str zeros bbstring)
        rows (vec (partition 8 fullstring))]
    (rows (- 7 (dec rank)))))

(defn get-file
  "Returns the given file from the given bitboard"
  [bb file]
  (let [bbstring (bb->string bb)
        zeros (prec0s bbstring)
        fullstring (str zeros bbstring)
        rows (partition 8 fullstring)]
    ((vec
      (partition 8 (apply str (apply interleave rows))))
      (dec file))))
