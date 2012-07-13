(ns chess.test.bitboard.utils
  (:use [clojure.test])
  (:use [chess.bitboard.utils] :reload))

(deftest test-bb-rank
  (is (= (bb-rank 3) 1))
  (is (= (bb-rank 32) 5))
  (is (= (bb-rank 63) 8)))

(deftest test-bb-file
  (is (= (bb-file 63) 1))
  (is (= (bb-file 45) 3))
  (is (= (bb-file 4) 4)))
