(ns chess.bitboard.data
  (:use [chess.bitboard.utils]))
  
(def knight-lookup 
  "
  Precalculated knight moves so they can be looked up by index

  user=> (print-bb (knight-lookup 26))
  00000000
  00000000
  00001010
  00010001
  00000000
  00010001
  00001010
  00000000
  " 
  [132096 329728 659712 1319424 
   2638848 5277696 10489856 4202496 
   33816580 84410376 168886289 337772578 
   675545156 1351090312 2685403152 1075839008 
   8657044482 21609056261 43234889994 86469779988 
   172939559976 345879119952 687463207072 275414786112 
   2216203387392 5531918402816 11068131838464 22136263676928 
   44272527353856 88545054707712 175990581010432 70506185244672 
   567348067172352 1416171111120896 2833441750646784 5666883501293568 
   11333767002587136 22667534005174272 45053588738670592 18049583422636032 
   145241105196122112 362539804446949376 725361088165576704 1450722176331153408 
   2901444352662306816 5802888705324613632 -6913025356609880064 4620693356194824192 
   288234782788157440 576469569871282176 1224997833292120064 2449995666584240128 
   4899991333168480256 -8646761407372591104 1152939783987658752 2305878468463689728 
   1128098930098176 2257297371824128 4796069720358912 9592139440717824 
   19184278881435648 38368557762871296 4679521487814656 9077567998918656])

(comment
  (def knight-lookup
    (apply vector
      (for [i (range 0 64)]
        (bit-xor
          ;NNE
          (if (and
                  (not= (bb-rank i) 7) ; if statements avoid going off the edges
                  (not= (bb-rank i) 8)
                  (not= (bb-file i) 8)) 
            (bit-shift-left 1 (+ i 15))
            0)
          ;NEE
          (if (and
                  (not= (bb-rank i) 8)
                  (not= (bb-file i) 7)
                  (not= (bb-file i) 8))
            (bit-shift-left 1 (+ i 6))
            0)
          ;SEE
          (if (and
                  (not= (bb-rank i) 1)
                  (not= (bb-file i) 7)
                  (not= (bb-file i) 8))
            (bit-shift-left 1 (- i 10))
            0)
          ;SSE
          (if (and
                  (not= (bb-rank i) 1)
                  (not= (bb-rank i) 2)
                  (not= (bb-file i) 8))
            (bit-shift-left 1 (- i 17))
            0)
          ;SSW
          (if (and
                  (not= (bb-rank i) 1)
                  (not= (bb-rank i) 2)
                  (not= (bb-file i) 1))
            (bit-shift-left 1 (- i 15))
            0)
          ;SWW
          (if (and
                  (not= (bb-rank i) 1)
                  (not= (bb-file i) 1)
                  (not= (bb-file i) 2))
            (bit-shift-left 1 (- i 6))
            0)
          ;NWW
          (if (and
                  (not= (bb-rank i) 8)
                  (not= (bb-file i) 1)
                  (not= (bb-file i) 2))
            (bit-shift-left 1 (+ i 10))
            0)
          ;NNW
          (if (and
                  (not= (bb-rank i) 7)
                  (not= (bb-rank i) 8)
                  (not= (bb-file i) 1))
            (bit-shift-left 1 (+ i 17))
            0))))))
