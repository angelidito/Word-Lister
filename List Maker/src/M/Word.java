package M;

public class Word {
	private LANG lang;
	private String word;
	private String info = "";

	public enum LANG {
		ES, EN, UNKNOWN
	}

	public Word(String word, LANG lang) {
		this.word = word.toLowerCase().trim();
		this.lang = lang;
	}

	public LANG getLang() {
		return this.lang;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word.trim();
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Word)) {
			return false;
		}
		Word word = (Word) o;
		return this.word.compareToIgnoreCase(word.getWord()) == 0;
	}

}
