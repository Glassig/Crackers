#!/bin/bash
pdflatex report.tex
bibtex report.aux
pdflatex report.tex
pdflatex report.tex
rm report.toc
rm report.aux
rm report.log
rm report.bbl
rm report.blg