(ns chess.bitboard.utils)

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
  (let [bbstring (java.lang.Long/toBinaryString bb)
        zeros (apply str (take (- 64 (count bbstring)) (repeat \0)))]
    (doseq [lst (map #(apply str %) (partition 8 (str zeros bbstring)))]
      (println lst))))

(defn bb-rank
  "Returns the rank from the given index"
  [index]
  (inc (quot index 8)))

(defn bb-file
  "Returns the file from the given index"
  [index]
  (- 8 (mod index 8)))
