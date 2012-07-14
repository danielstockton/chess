(ns chess.bitboard.data
  (:use [chess.bitboard.utils :as bbutils :only [index->rank index->file]]))
  
; TODO complete function
(def file-lookup
  "Pre-calculated file attacks with occupancy lookup"
  )

(def knight-lookup 
  "
  Pre-calculated knight moves so they can be looked up by index

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

(def king-lookup
  "
  Pre-calculated king moves so they can be looked up by index
  "
  [770 1797 3594 7188
   14376 28752 57504 49216
   197123 460039 920078 1840156
   3680312 7360624 14721248 12599488
   50463488117769984 235539968 471079936
   942159872 1884319744 3768639488 3225468928
   12918652928 30149115904 60298231808 120596463616
   241192927232 482385854464 964771708928 825720045568
   3307175149568 7718173671424 15436347342848 30872694685696
   61745389371392 123490778742784 246981557485568 211384331665408
   846636838289408 1975852459884544 3951704919769088 7903409839538176
   15806819679076352 31613639358152704 63227278716305408 54114388906344448
   216739030602088448 505818229730443264 1011636459460886528 2023272918921773056
   4046545837843546112 8093091675687092224 -2260560722335367168 -4593460513685372928
   144959613005987840 362258295026614272 724516590053228544 1449033180106457088
   2898066360212914176 5796132720425828352 -6854478632857894912 4665729213955833856])

(comment
  (def king-lookup
    (apply vector
      (for [i (range 0 64)]
        (bit-xor
          ;N
          (if (not= (bbutils/index->rank i) 8)
            (bit-shift-left 1 (+ i 8))
            0)
          ;NE
          (if (and
                (not= (bbutils/index->rank i) 8)
                (not= (bbutils/index->file i) 8))
            (bit-shift-left 1 (+ i 7))
            0)
          ;E
          (if (not= (bbutils/index->file i) 8)
            (bit-shift-left 1 (- i 1))
            0)
          ;SE
          (if (and
                (not= (bbutils/index->rank i) 1)
                (not= (bbutils/index->file i) 8))
            (bit-shift-left 1 (- i 9))
            0)
          ;S
          (if (not= (bbutils/index->rank i) 1)
            (bit-shift-left 1 (- i 8))
            0)
          ;SW
          (if (and
                (not= (bbutils/index->rank i) 1)
                (not= (bbutils/index->file i) 1))
            (bit-shift-left 1 (- i 7))
            0)
          ;W
          (if (not= (bbutils/index->file i) 1)
            (bit-shift-left 1 (+ i 1))
            0)
          ;NW
          (if (and
                (not= (bbutils/index->rank i) 8)
                (not= (bbutils/index->file i) 1))
            (bit-shift-left 1 (+ i 9))
            0))))))

(comment
  (def knight-lookup
    (apply vector
      (for [i (range 0 64)]
        (bit-xor
          ;NNE
          (if (and
                  (not= (bbutils/index->rank i) 7) ; if statements avoid going off the edges
                  (not= (bbutils/index->rank i) 8)
                  (not= (bbutils/index->file i) 8)) 
            (bit-shift-left 1 (+ i 15))
            0)
          ;NEE
          (if (and
                  (not= (bbutils/index->rank i) 8)
                  (not= (bbutils/index->file i) 7)
                  (not= (bbutils/index->file i) 8))
            (bit-shift-left 1 (+ i 6))
            0)
          ;SEE
          (if (and
                  (not= (bbutils/index->rank i) 1)
                  (not= (bbutils/index->file i) 7)
                  (not= (bbutils/index->file i) 8))
            (bit-shift-left 1 (- i 10))
            0)
          ;SSE
          (if (and
                  (not= (bbutils/index->rank i) 1)
                  (not= (bbutils/index->rank i) 2)
                  (not= (bbutils/index->file i) 8))
            (bit-shift-left 1 (- i 17))
            0)
          ;SSW
          (if (and
                  (not= (bbutils/index->rank i) 1)
                  (not= (bbutils/index->rank i) 2)
                  (not= (bbutils/index->file i) 1))
            (bit-shift-left 1 (- i 15))
            0)
          ;SWW
          (if (and
                  (not= (bbutils/index->rank i) 1)
                  (not= (bbutils/index->file i) 1)
                  (not= (bbutils/index->file i) 2))
            (bit-shift-left 1 (- i 6))
            0)
          ;NWW
          (if (and
                  (not= (bbutils/index->rank i) 8)
                  (not= (bbutils/index->file i) 1)
                  (not= (bbutils/index->file i) 2))
            (bit-shift-left 1 (+ i 10))
            0)
          ;NNW
          (if (and
                  (not= (bbutils/index->rank i) 7)
                  (not= (bbutils/index->rank i) 8)
                  (not= (bbutils/index->file i) 1))
            (bit-shift-left 1 (+ i 17))
            0))))))
