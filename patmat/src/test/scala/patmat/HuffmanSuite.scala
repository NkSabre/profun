package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    
    val codeTable1 = List(('a',List(0)),('b', List(1)))
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
  
  test("codeBits should work well") {
    new TestTrees {
      assert(codeBits(codeTable1)('a') === List(0))
      assert(codeBits(codeTable1)('b') === List(1))
    }
  }
  
  test("convert should convert a tree to a codetable") {
    new TestTrees {
      assert(convert(t1) === codeTable1)
    }
  }
  
  test("quickEncode should encode a string as idenitical as an encode") {
    new TestTrees {
      assert(quickEncode(t1)("ab".toList) === encode(t1)("ab".toList))
    }
  }
  
  test("encode should encode a string as idenitical as an encode") {
    assert(secret === encode(frenchCode)(decodedSecret))
  }
  
  test("test createTree") {
    val codeTree = createCodeTree("The frequency of letters in text has often been studied for use in cryptanalysis, and frequency analysis in particular. No exact letter frequency distribution underlies a given language, since all writers write slightly differently. Linotype machines assumed the letter order, from most to least common, to be etaoin shrdlu cmfwyp vbgkjq xz based on the experience and custom of manual compositors. Likewise, Modern International Morse code encodes the most frequent letters with the shortest symbols; arranging the Morse alphabet into groups of letters that require equal amounts of time to transmit, and then sorting these groups in increasing order, yields e it san hurdm wgvlfbk opjxcz yq. Similar ideas are used in modern data-compression techniques such as Huffman coding.".toList)
    
    println(encode(codeTree)(decodedSecret).length)
    assert(decode(codeTree, encode(codeTree)(decodedSecret)) === decodedSecret )
  }
}
