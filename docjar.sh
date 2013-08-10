#!/bin/bash
#docjar.sh
# compiles, javadocs, and jars a package

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
PACKPATH=$DIR
JARPATH=$DIR/jars
PACKNAME=$1
DOCPATH=$PACKPATH/$PACKNAME/docs
SOURCEPATH=$PACKPATH/$PACKNAME/src
OUTPATH=$PACKPATH/$PACKNAME/

compile()
{
	cd $SOURCEPATH
	find . -name \*.java -exec javac -d $OUTPATH {} \+
	return $?
}

doc()
{
	cd $SOURCEPATH
	javadoc -d $DOCPATH -subpackages $PACKNAME
	return $?
}

jjar()
{
	cd $PACKPATH/$PACKNAME
	jar cvf $JARPATH/$PACKNAME.jar $PACKNAME src docs || return 6
	return $?
}



[ -z $PACKNAME ] && echo Provide a package name && exit 1

[ ! -d $PACKPATH/$PACKNAME ] && echo Invalid package $PACKPATH/$PACKNAME && exit 2

[ ! -d $SOURCEPATH ] && echo Put .java files in $SOURCEPATH && exit 3


echo Compiling...
find $OUTPATH -name \*.class -exec rm {} \;
compile
if [[ $? != 0 ]]
then
	echo Compile failed.
	exit 4
fi
echo Compile complete.; echo

[ ! -e $DOCPATH ] && mkdir $DOCPATH
echo Generating javadocs...
doc
if [[ $? != 0 ]]
then
	echo javadoc failed.
	exit 5
fi
echo javadocs generated.; echo

echo Creating $PACKNAME.jar...
jjar
if [[ $? != 0 ]]
then
	echo jar failed.
	exit 8
fi
echo $PACKNAME.jar created
exit 0
