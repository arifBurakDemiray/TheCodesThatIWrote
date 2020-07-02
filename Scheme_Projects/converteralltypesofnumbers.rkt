#lang racket
;Arif Burak Demiray
;Converting list members to a integer number
(define (list->num l)
  (let loop ((n 0) (l l))
    (if (empty? l)
        n
        (loop (+ (* 10 n) (car l)) (cdr l)))))
;Decimal to binary function
(define (dec->bin n)
  (letrec ((recc (lambda (acc n)
                   (cond ((zero? n) acc)
                         (else (recc (cons (remainder n 2) acc)
                                     (quotient n 2)))))))
    (recc '() n)))
;Decimal to octal number
(define (dec->octal n)
  (if (< n 4)
      (list n)
      (append (dec->octal (floor (/ n 8)))
              (list (remainder n 8)))))
;Decimal to hexadecimal number
(define (dec->hex num)
  (letrec ((recc (lambda (acc num)
                   (cond ((zero? num) acc)
                         (else (recc (cons (list-ref '("0" "1" "2" "3" "4" "5" "6" "7" "8" "9" "A" "B" "C" "D" "E" "F" "G") (remainder num 16)) acc)
                                     (quotient num 16)))))))
   (string-join (map ~a (recc '() num)) "")))
;Binary to decimal number
(define (bin->dec n)
  (if (zero? n)
      n
      (+ (modulo n 10) (* 2 (bin->dec (quotient n 10))))))
;Binary to octal number
(define (bin->octal binaryn)
  (list->num (dec->octal(bin->dec binaryn))))
;Binary to hexadecimal number
(define (bin->hex binaryn)
  (dec->hex (bin->dec binaryn)))
;Converting the hex chars to numbers
(define (char->hex-digit c)
  (cond [(char<=? #\0 c #\9)
         (- (char->integer c) (char->integer #\0))]
        [(char<=? #\A c #\F)
         (+ 10 (- (char->integer c) (char->integer #\A)))]
        [(char<=? #\a c #\f)
         (+ 10 (- (char->integer c) (char->integer #\a)))]
        [else
         (error 'char->hex-digit "~A is not a hex character" c)]))
;Hexadecimal to decimal number
(define (hex->dec hs)
  (if (=  (string-length hs) 0) 0
    (+ (* (char->hex-digit (string-ref hs 0)) (expt 16 (- (string-length hs) 1))) (if (=  (string-length hs)  0 )0(hex->dec (substring hs 1  (string-length hs) ))))))
;Hexadecimal to binary numbrt
(define (hex->bin n)
  (dec->bin (hex->dec n)))
;Hexadecimal to octal number
(define (hex->octal n)
  (list->num (dec->octal (hex->dec n))))
;Octal to decimal number
(define (octal->dec n)
  (if (zero? n)
      n
      (+ (modulo n 10) (* 8 (octal->dec (quotient n 10))))))
;Octal to binary number
(define (octal->bin octnum)
  (dec->bin (octal->dec octnum)))
;Octal to hexadecimal number
(define (octal->hex octnum)
  (dec->hex (octal->dec 256)))
;Main function
;Welcome to number conversion program.
;In this program you can convert dec->bin,dec->hex,dec->octal,bin->dec,bin->hex,bin->octal,hex->dec,hex->bin,hex->octal,octal->dec,octal->bin,octal->hex
;You must give integer input when binary, decimal and octal conversions.
;You must give string input when hex->dec,hex->octal and hex->dec conversions.
(define (num-converter term func)
  (func term))
(num-converter 1100 octal->bin)
(num-converter 1100 octal->hex)
(num-converter 1100 octal->dec)
(num-converter 1100 dec->bin)
(num-converter 1100 dec->hex)
(num-converter 1100 dec->octal)
(num-converter "1100" hex->bin)
(num-converter "1100" hex->dec)
(num-converter "1100" hex->octal)
(num-converter 1100 bin->hex)
(num-converter 1100 bin->dec)
(num-converter 1100 bin->octal)
