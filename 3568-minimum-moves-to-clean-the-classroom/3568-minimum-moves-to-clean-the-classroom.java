class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        // Give every litter cell a bit position.
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int startRow = -1;
        int startCol = -1;
        int litterCount = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    startRow = r;
                    startCol = c;
                } else if (ch == 'L') {
                    litterId[r][c] = litterCount++;
                }
            }
        }

        int allMask = (1 << litterCount) - 1;

        // BFS state:
        // row, col, mask, remaining energy
        //
        // Encode everything into a long:
        // row      -> 6 bits
        // col      -> 6 bits
        // mask     -> 10 bits
        // energy   -> 6 bits
        ArrayDeque<Long> queue = new ArrayDeque<>();

        /*
         * visited[row][col][mask][energy]
         */
        boolean[][][][] visited = new boolean[m][n][1 << litterCount][energy + 1];

        queue.offer(encode(startRow, startCol, 0, energy));
        visited[startRow][startCol][0][energy] = true;

        int[][] directions = {
                { 1, 0 },
                { -1, 0 },
                { 0, 1 },
                { 0, -1 }
        };

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process one BFS level = one number of moves
            while (size-- > 0) {

                long state = queue.poll();

                int[] decoded = decode(state);

                int r = decoded[0];
                int c = decoded[1];
                int mask = decoded[2];
                int remainingEnergy = decoded[3];

                // All litter collected
                if (mask == allMask) {
                    return moves;
                }

                for (int[] dir : directions) {

                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Outside grid
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // Cannot move without energy
                    if (remainingEnergy == 0) {
                        continue;
                    }

                    int nextEnergy = remainingEnergy - 1;
                    int nextMask = mask;

                    // Collect litter if this cell has litter
                    if (litterId[nr][nc] != -1) {
                        nextMask |= (1 << litterId[nr][nc]);
                    }

                    // Reset energy on R
                    if (classroom[nr].charAt(nc) == 'R') {
                        nextEnergy = energy;
                    }

                    if (!visited[nr][nc][nextMask][nextEnergy]) {

                        visited[nr][nc][nextMask][nextEnergy] = true;

                        queue.offer(
                                encode(nr, nc, nextMask, nextEnergy));
                    }
                }
            }

            moves++;
        }

        return -1;
    }

    private long encode(int r, int c, int mask, int energy) {
        long state = 0;

        state |= r;
        state |= ((long) c << 6);
        state |= ((long) mask << 12);
        state |= ((long) energy << 22);

        return state;
    }

    private int[] decode(long state) {

        int r = (int) (state & 63);
        int c = (int) ((state >> 6) & 63);
        int mask = (int) ((state >> 12) & 1023);
        int energy = (int) ((state >> 22) & 63);

        return new int[] { r, c, mask, energy };
    }
}