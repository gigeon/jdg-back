package jtw.jtwback.save.service;

import jtw.jtwback.com.Block;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaveService {
    private final List<Block> chain = new ArrayList<>();

    public SaveService() {
        // 최초 제네시스 블록
        chain.add(new Block(0, "Genesis Block", "0"));
    }

    public List<Block> getChain() {
        return chain;
    }

    public Block addBlock(String data) {
        Block lastBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(chain.size(), data, lastBlock.getHash());
        chain.add(newBlock);
        return newBlock;
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block current = chain.get(i);
            Block previous = chain.get(i - 1);

            if (!current.getHash().equals(current.calculateHash())) return false;
            if (!current.getPreviousHash().equals(previous.getHash())) return false;
        }
        return true;
    }
}
