(ns chess.test.bitboard.utils
  (:use [clojure.test])
  (:use [chess.bitboard.utils] :reload))

(deftest test-rank-bb
  (is (= (rank-bb 3) 16711680))
  (is (= (rank-bb 7) 71776119061217280)))

(deftest test-file-bb
  (is (= (file-bb 3) 2314885530818453536))
  (is (= (file-bb 7) 144680345676153346)))

(deftest test-bitscan
  (is (= (bitscan- 2r1000001000) 9))
  (is (= (bitscan+ 2r1000001000) 3)))

(deftest test-bb->string
  (is (= (bb->string 3213) "110010001101"))
  (is (= (bb->string 32) "100000")))

(deftest test-preceding-zeros
  (is (= (preceding-zeros "100")
         "0000000000000000000000000000000000000000000000000000000000000")))

(deftest test-index->rank
  (is (= (index->rank 3) 1))
  (is (= (index->rank 32) 5))
  (is (= (index->rank 63) 8)))

(deftest test-index->file
  (is (= (index->file 63) 1))
  (is (= (index->file 45) 3))
  (is (= (index->file 4) 4)))

(deftest test-get-rank
  (is (= (get-rank 2638848 2) '(\0 \1 \0 \0 \0 \1 \0 \0)))
  (is (= (get-rank 0 1) '(\0 \0 \0 \0 \0 \0 \0 \0))))

(deftest test-get-file
  (is (= (get-file 2638848 3) '(\0 \0 \0 \0 \0 \1 \0 \0)))
  (is (= (get-file 2638848 2) '(\0 \0 \0 \0 \0 \0 \1 \0)))
  (is (= (get-file 0 1) '(\0 \0 \0 \0 \0 \0 \0 \0))))
