package com.mltech.laf.processing;

import com.mltech.laf.document.Document;
import com.mltech.laf.parser.DocumentParser;


public class SentenceSplitter extends DocumentParser {

	@Override
	protected void parse(Document document) {
		// TODO Auto-generated method stub
		
	}
//	if ($language eq "JA")
//	{
//		open LEMM, ">:utf8", "$file.$lemmExt.txt";
//		while(<IN>)
//		{
//			chomp;
//			$jp_utf .= $_;
//		}
//		
//		while ($jp_utf =~ /([！-～Α-Ωα-ω]+)[\t ]+([！-～Α-Ωα-ω]+)/)
//		{
//	        $jp_utf =~ s/([！-～Α-Ωα-ω]+)[\t ]+([！-～Α-Ωα-ω]+)/$1<!!>$2/g;
//		}
//		#//remove spaces
//		$jp_utf =~ s/ +//g;
//		
//		$jp_euc = encode("euc-jp", $jp_utf);
//		$jp_euc = Text::ChaSen::sparse_tostr($jp_euc);
//		$jp_utf = decode("euc-jp", $jp_euc);
//
//		foreach $line (split(/\n/, $jp_utf))
//		{
//			($surface, $root, $cat) = split(/;/, $line);
//			if (defined($cat) and ($cat < 59 or $cat > 73))
//			{
//				$lemm .= ($root =~ /^([$seg_jp])$/) ? "$1\n" : " $root";
//			}
//			$out .= ($surface =~ /^([$seg_jp])$/) ? "$1\n" : " $surface" if $cat != "";
//		}
//		$lemm = lc($lemm);
//		$out = lc($out);# . "[$cat:$surface]\n";
//		
//
//=cmt
//		while(<IN>)
//		{
//			chomp;
//			my @sentences = split(/([$seg_jp])/);
//			if ($#sentences <= 0)
//			{
//				$sentences[$i] = $_;
//				$sentences[$i + 1] = "";
//			}
//		
//			for ($i=0; $i<$#sentences; $i+=2)
//			{
//				$jp_utf .= $sentences[$i].$sentences[$i + 1]."\n";
//			}
//		}
//=cut
//
//		print LEMM $lemm;
//		print OUT $out;
//		close OUT;
//		close LEMM;
//	}
//	
//	if ($language eq "EN")
//	{
//=cmt
//		while(<IN>)
//		{
//			chomp;
//
//			foreach my $ab (keys %abbr)
//			{
//				s/^\Q$ab\E\s+/$abbr{$ab} /ig;
//				s/\s+\Q$ab\E\s+/ $abbr{$ab} /ig;
//			}
//
//
//			my @sentences = split(/([$seg_en])\s+/);
//			foreach my $se (@sentences)
//			{
//				$se =~ /^([$seg_en\n])$/;
//				if ($se =~ /^([$seg_en])$/)
//				{
//					print OUT "$1\n";
//				}
//				else
//				{
//					$se =~ s/$abbr_extension//g;
//					#$se = "$se.";
//					$se =~ s/\.+$/./;
//					print OUT "$se";
//				}
//			}
//		}
//		close OUT;
//=cut
//
//		$tok = lc(`perl tokenizer/tokenizer.pl <$file.$self->{INPUTEXT}.txt`);
//		$tok = decode("utf8", $tok);
//		
//		my @sentences = split(/ ([$seg_en])\s+/, $tok);
//		foreach my $se (@sentences)
//		{
//				$se =~ /^([$seg_en\n])$/;
//				if ($se =~ /^([$seg_en])$/)
//				{
//					print OUT " $1\n";
//				}
//				else
//				{
//					$se =~ s/\.+$/./;
//					print OUT "$se";
//				}
//		}
//		#print OUT $tok;
//		close OUT;
//
//		$cmd = "cd ".$self->cd."/axis.EN; ./axis.EN $file.$self->{OUTPUTEXT}.txt > $file.$lemmExt.txt";
//		`$cmd`;
//	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}
}
