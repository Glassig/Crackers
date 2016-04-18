#!/bin/bash
pdflatex report.tex
echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
bibtex report.aux
echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
pdflatex report.tex
echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
pdflatex report.tex
echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
rm report.toc
rm report.aux
rm report.log
rm report.bbl
rm report.blg
echo ""