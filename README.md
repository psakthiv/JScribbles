I would like to express my sincere gratitude for the trust you have consistently placed in me. Over the past cycle, you have given me several opportunities—across ASR, Seer, Mandate View, Intrinsic Taxonomy, and more—that have significantly expanded my exposure and helped me grow both technically and as a problem solver.

One of the things I admire most about your leadership is your clarity of vision. You always see the bigger picture long before the rest of us get there. Initiatives like Mandate View and Content Search are strong examples of how your forward-looking thinking shapes the direction of our team and ensures we stay ahead of what the business needs. Your ability to articulate where we need to go—and more importantly, why—gives the team confidence and purpose.

At the same time, you give us the space to explore our own approaches. While you clearly outline the end goal and the path to get started, you allow us the freedom to try, iterate, and innovate. This balance of guidance and autonomy not only empowers us but also helps us design solutions that are scalable and sustainable in the long term.

I genuinely appreciate how intentional you are about identifying potential within the team. The way you encourage us to think independently, take ownership, and make architectural decisions is shaping the next line of leaders. This level of trust and mentorship has made a meaningful impact on my growth, and I am grateful for it.
 
 
 
 import re
import unicodedata

# You can add/remove words from this set as you like
STOPWORDS = {
    "yes", "was", "am", "is", "are",
    "the", "a", "an",
    "this", "that", "these", "those",
    "and", "or", "but",
    "to", "for", "on", "in", "with", "at", "by", "from",
    "it", "its",
    "have", "has", "had",
    "be", "been", "being",
    "do", "does", "did",
    "will", "would", "can", "could",
    "shall", "should", "may", "might",
    "you", "i", "we", "they", "he", "she", "them", "him", "her", "me", "my",
    "our", "your", "their"
}

def remove_stopwords(text: str) -> str:
    words = text.split()
    filtered = []
    for w in words:
        # strip simple punctuation around the word for matching
        core = w.strip(".,;:/-()[]{}\"'")
        if core.lower() not in STOPWORDS:
            filtered.append(w)
    return " ".join(filtered)

def clean_ocr_text_for_embeddings(text: str) -> str:
    """
    Clean OCR text while keeping names, labels, dates, and numbers.
    Also removes common filler words like 'yes', 'was', 'am', etc.
    """

    # 1. Normalize unicode (fix odd characters)
    text = unicodedata.normalize("NFKC", text)

    # 2. Work line-by-line to drop exact duplicate lines (repeated headings)
    lines = text.splitlines()
    seen = set()
    unique_lines = []
    for line in lines:
        stripped = line.strip()
        if not stripped:
            continue
        if stripped not in seen:
            seen.add(stripped)
            unique_lines.append(stripped)

    text = " ".join(unique_lines)

    # 3. Fix hyphen + newline breaks like "Natio-\nnal" → "National"
    text = re.sub(r"-\s+", "", text)

    # 4. Collapse whitespace
    text = re.sub(r"\s+", " ", text)

    # 5. Remove repeated punctuation (//// → /, --- → -)
    text = re.sub(r"([\/\-\:\;\,\.\_])\1+", r"\1", text)

    # 6. Remove random non-alphanumeric garbage, but keep / - . , : ;
    text = re.sub(r"[^\w\s\/\-\.,:;]", "", text)

    # 7. Collapse spaces again
    text = re.sub(r"\s+", " ", text).strip()

    # 8. Remove filler words like yes/was/am/etc.
    text = remove_stopwords(text)

    return text
