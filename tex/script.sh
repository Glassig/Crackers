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
if [ "$(uname)" == "Darwin" ]; then
    # Do something under Mac OS X platform
    open report.pdf        
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
    # Do something under GNU/Linux platform
    gnome-open report.pdf

fi