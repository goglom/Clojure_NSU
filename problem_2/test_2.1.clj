(ns test_2.1
  (:use impl_2.1)
  (:require [clojure.test :as test]))

(test/deftest problem_2.1-test
  (test/testing "Testing of nth-prime function"
    (test/is (= 2 (prime-nth 1)))
    (test/is (= 3 (prime-nth 2)))
    (test/is (= 5 (prime-nth 3)))
    (test/is (= 7 (prime-nth 4)))
    (test/is (= 11 (prime-nth 5)))
    (test/is (= 13 (prime-nth 6)))
    (test/is (= 17 (prime-nth 7)))
    (test/is (= 541 (prime-nth 100)))))

(test/run-tests 'test_2.1)
