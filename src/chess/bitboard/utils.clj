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
  (let [bbstring (java.lang.Long/toBinaryString bb)]
    (doseq [lst (map #(apply str %) (partition 8 (str (apply str (take (- 64 (count bbstring)) (repeat \0))) bbstring)))]
      (println lst))))
