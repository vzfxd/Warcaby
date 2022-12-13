package pl.warcaby.Checkers.Variations;

import pl.warcaby.Checkers.*;
import java.util.ArrayList;

public class SpanishBoard extends Board {

    public SpanishBoard(Player whitePlayer, Player blackPlayer){
        this.width = 8;
        this.heigth = 8;
        this.fields = new Field[8][8];
        for(int y = 0; y<8;y++){
            for(int x = 0; x<8;x++){
                if(y<3 && (x+y)%2==1) {
                    this.fields[x][y] = new Field(new Pawn(whitePlayer));
                }
                else if(y>4 && (x+y)%2==1){
                    this.fields[x][y] = new Field(new Pawn(blackPlayer));
                }
                else{
                    this.fields[x][y] = new Field();
                }
            }
        }
        this.turn = Color.WHITE;
    }
    @Override
    public ArrayList<int[]> checkBestPawnMoves(int[] pawnLocation) {
        ArrayList<int[]> bestMoves =  new ArrayList<>();
        int x = pawnLocation[0];
        int y = pawnLocation[1];
        int bestMoveLength = 0;
        if(this.fields[x][y].getPawnColor()==this.turn){
            ArrayList<ArrayList<int[]>> allMoves = getAllMoves(pawnLocation);
            for (ArrayList<int[]> move : allMoves) {
                int len = move.get(0)[0];
                if (len > bestMoveLength) {
                    bestMoves = new ArrayList<>();
                    for (int j = 0; j < len + 2; j++) {
                        bestMoves.add(move.get(j));
                    }
                    bestMoveLength = len;
                } else if (len == bestMoveLength) {
                    int lim = len + 2;
                    if (len == 0) {
                        lim = 3;
                    }
                    for (int j = 2; j < lim; j++) {
                        bestMoves.add(move.get(j));
                    }
                }
            }
            return bestMoves;

        }
        else return null;
    }

    @Override
    protected ArrayList<ArrayList<int[]>> getAllMoves(int[] pawnLocation) {
        int x = pawnLocation[0];
        int y = pawnLocation[1];
        Color pawnColor = this.fields[x][y].getPawnColor();
        PawnType pawnType = this.fields[x][y].getPawnType();
        ArrayList<ArrayList<int[]>> allMoves = new ArrayList<>();
        Color enemyPawnColor;
        if(pawnColor==Color.BLACK){
            enemyPawnColor=Color.WHITE;
        }
        else{enemyPawnColor=Color.BLACK;}

        //Zwykle pionki

        if(pawnType==PawnType.NORMAL){

            //Biale pionki

            if(pawnColor==Color.WHITE){

                //ruch w lewo

                if(x>0 && this.fields[x-1][y+1].getPawnColor()==null){
                    ArrayList<int[]> move = new ArrayList<>();
                    move.add(new int[]{0, 0});
                    move.add(new int[]{x,y});
                    move.add(new int[]{x-1,y+1});
                    allMoves.add(move);
                }

                //ruch w prawo
                if(x<7 && this.fields[x+1][y+1].getPawnColor()==null){
                    ArrayList<int[]> move = new ArrayList<>();
                    move.add(new int[]{0, 0});
                    move.add(new int[]{x,y});
                    move.add(new int[]{x+1,y+1});
                    allMoves.add(move);
                }

                //bicie w lewo

                if(x>1 && this.fields[x-1][y+1].getPawnColor()==enemyPawnColor && this.fields[x-2][y+2].getPawnColor()==null){
                    this.fields[x-2][y+2].setOccupied(this.fields[x][y].getOccupied());
                    ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{x-2,y+2});
                    int moreways = 0;
                    for(ArrayList<int[]> mv : fullMove){
                        if(mv.get(0)[0]!=0){
                            moreways++;
                            ArrayList<int[]> move = new ArrayList<>();
                            move.add(new int[]{mv.get(0)[0]+1,mv.get(0)[0]+1});
                            move.add(new int[]{x,y});
                            for(int i=1; i<mv.size();i++){
                                move.add(mv.get(i));
                            }
                            allMoves.add(move);
                        }
                    }
                    //koniec sciezki bicia
                    if(moreways==0){
                        ArrayList<int[]> move = new ArrayList<>();
                        move.add(new int[]{1, 1});
                        move.add(new int[]{x,y});
                        move.add(new int[]{x-2,y+2});
                        allMoves.add(move);
                    }
                    this.fields[x-2][y+2].setOccupied(null);

                }

                //bicie w prawo

                if(x<6 && this.fields[x+1][y+1].getPawnColor()==enemyPawnColor && this.fields[x+2][y+2].getPawnColor()==null){
                    this.fields[x+2][y+2].setOccupied(this.fields[x][y].getOccupied());
                    ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{x+2,y+2});
                    int moreways = 0;
                    for(ArrayList<int[]> mv : fullMove){
                        if(mv.get(0)[0]!=0){
                            moreways++;
                            ArrayList<int[]> move = new ArrayList<>();
                            move.add(new int[]{mv.get(0)[0]+1,mv.get(0)[0]+1});
                            move.add(new int[]{x,y});
                            for(int i=1; i<mv.size();i++){
                                move.add(mv.get(i));
                            }
                            allMoves.add(move);
                        }
                    }
                    //koniec sciezki bicia
                    if(moreways==0){
                        ArrayList<int[]> move = new ArrayList<>();
                        move.add(new int[]{1, 1});
                        move.add(new int[]{x,y});
                        move.add(new int[]{x+2,y+2});
                        allMoves.add(move);
                    }
                    this.fields[x+2][y+2].setOccupied(null);
                }

            }

            //Czarne pionki

            else{

                //ruch w lewo

                if(x>0 && this.fields[x-1][y-1].getPawnColor()==null){
                    ArrayList<int[]> move = new ArrayList<>();
                    move.add(new int[]{0, 0});
                    move.add(new int[]{x,y});
                    move.add(new int[]{x-1,y-1});
                    allMoves.add(move);
                }

                //ruch w prawo
                if(x<7 && this.fields[x+1][y-1].getPawnColor()==null){
                    ArrayList<int[]> move = new ArrayList<>();
                    move.add(new int[]{0, 0});
                    move.add(new int[]{x,y});
                    move.add(new int[]{x+1,y-1});
                    allMoves.add(move);
                }

                //bicie w lewo

                if(x>1 && this.fields[x-1][y+1].getPawnColor()==enemyPawnColor && this.fields[x-2][y-2].getPawnColor()==null){
                    this.fields[x-2][y-2].setOccupied(this.fields[x][y].getOccupied());
                    ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{x-2,y-2});
                    int moreways = 0;
                    for(ArrayList<int[]> mv : fullMove){
                        if(mv.get(0)[0]!=0){
                            moreways++;
                            ArrayList<int[]> move = new ArrayList<>();
                            move.add(new int[]{mv.get(0)[0]+1,mv.get(0)[0]+1});
                            move.add(new int[]{x,y});
                            for(int i=1; i<mv.size();i++){
                                move.add(mv.get(i));
                            }
                            allMoves.add(move);
                        }
                    }
                    //koniec sciezki bicia
                    if(moreways==0){
                        ArrayList<int[]> move = new ArrayList<>();
                        move.add(new int[]{1, 1});
                        move.add(new int[]{x,y});
                        move.add(new int[]{x-2,y-2});
                        allMoves.add(move);
                    }
                    this.fields[x-2][y-2].setOccupied(null);

                }

                //bicie w prawo

                if(x<6 && this.fields[x+1][y-1].getPawnColor()==enemyPawnColor && this.fields[x+2][y-2].getPawnColor()==null){
                    this.fields[x+2][y-2].setOccupied(this.fields[x][y].getOccupied());
                    ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{x+2,y-2});
                    int moreways = 0;
                    for(ArrayList<int[]> mv : fullMove){
                        if(mv.get(0)[0]!=0){
                            moreways++;
                            ArrayList<int[]> move = new ArrayList<>();
                            move.add(new int[]{mv.get(0)[0]+1,mv.get(0)[0]+1});
                            move.add(new int[]{x,y});
                            for(int i=1; i<mv.size();i++){
                                move.add(mv.get(i));
                            }
                            allMoves.add(move);
                        }
                    }
                    //koniec sciezki bicia
                    if(moreways==0){
                        ArrayList<int[]> move = new ArrayList<>();
                        move.add(new int[]{1, 1});
                        move.add(new int[]{x,y});
                        move.add(new int[]{x+2,y-2});
                        allMoves.add(move);
                    }
                    this.fields[x-2][y+2].setOccupied(null);

                }

            }
        }

        //Pionki typu QUEEN

        else if(pawnType==PawnType.QUEEN){
                //szukanie bicia gora lewo
                int nx = x-1;
                int ny = y+1;
                while(nx>0 && ny<7){
                    if(this.fields[nx][ny].getPawnColor()==pawnColor){
                        break;
                    }
                    if(this.fields[nx][ny].getPawnColor()==enemyPawnColor && this.fields[nx-1][ny+1].getPawnColor()==null){
                        ArrayList<int[]> move = new ArrayList<>();
                        int moreways = 0;
                        this.fields[nx-1][ny+1].setOccupied(this.fields[x][y].getOccupied());
                        ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{nx-1,ny+1});
                        for(ArrayList<int[]> mv : fullMove){
                            if(mv.get(0)[0]>0){
                                moreways++;
                                move.add(new int[]{mv.get(0)[0]+1, mv.get(0)[0]+1});
                                move.add(pawnLocation);
                                for(int i = 1; i<mv.size();i++){
                                    move.add(mv.get(i));
                                }
                                allMoves.add(move);
                            }
                        }
                        if(moreways==0){
                            move.add(new int[]{1,1});
                            move.add(pawnLocation);
                            move.add(new int[]{nx-1,ny+1});
                        }
                        this.fields[nx-1][ny+1].setOccupied(null);
                    }
                    else{
                        ArrayList<int[]> move = new ArrayList<>();
                        move.add(new int[]{0,0});
                        move.add(pawnLocation);
                        move.add(new int[]{nx,ny});
                        allMoves.add(move);
                        nx--;
                        ny++;
                    }
                }

                //szukanie bicia dol lewo

                nx = x-1;
                ny = y-1;
                while(nx>0 && ny>0){
                    if(this.fields[nx][ny].getPawnColor()==pawnColor){
                        break;
                    }
                    if(this.fields[nx][ny].getPawnColor()==enemyPawnColor && this.fields[nx-1][ny-1].getPawnColor()==null){
                        ArrayList<int[]> move = new ArrayList<>();
                        int moreways = 0;
                        this.fields[nx-1][ny-1].setOccupied(this.fields[x][y].getOccupied());
                        ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{nx-1,ny-1});
                        for(ArrayList<int[]> mv : fullMove){
                            if(mv.get(0)[0]>0){
                                moreways++;
                                move.add(new int[]{mv.get(0)[0]+1, mv.get(0)[0]+1});
                                move.add(pawnLocation);
                                for(int i = 1; i<mv.size();i++){
                                    move.add(mv.get(i));
                                }
                                allMoves.add(move);
                            }
                        }
                        if(moreways==0){
                            move.add(new int[]{1,1});
                            move.add(pawnLocation);
                            move.add(new int[]{nx-1,ny-1});
                        }
                        this.fields[nx-1][ny-1].setOccupied(null);
                    }
                    else{
                        ArrayList<int[]> move = new ArrayList<>();
                        move.add(new int[]{0,0});
                        move.add(pawnLocation);
                        move.add(new int[]{nx,ny});
                        allMoves.add(move);
                        nx--;
                        ny--;
                    }
                }

                //szukanie bicia gora prawo

                nx = x+1;
                ny = y+1;
                while(nx<7 && ny<7){
                    if(this.fields[nx][ny].getPawnColor()==pawnColor){
                        break;
                    }
                    if(this.fields[nx][ny].getPawnColor()==enemyPawnColor && this.fields[nx+1][ny+1].getPawnColor()==null){
                        ArrayList<int[]> move = new ArrayList<>();
                        int moreways = 0;
                        this.fields[nx+1][ny+1].setOccupied(this.fields[x][y].getOccupied());
                        ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{nx+1,ny+1});
                        for(ArrayList<int[]> mv : fullMove){
                            if(mv.get(0)[0]>0){
                                moreways++;
                                move.add(new int[]{mv.get(0)[0]+1, mv.get(0)[0]+1});
                                move.add(pawnLocation);
                                for(int i = 1; i<mv.size();i++){
                                    move.add(mv.get(i));
                                }
                                allMoves.add(move);
                            }
                        }
                        if(moreways==0){
                            move.add(new int[]{1,1});
                            move.add(pawnLocation);
                            move.add(new int[]{nx+1,ny+1});
                        }
                        this.fields[nx+1][ny+1].setOccupied(null);
                    }
                    else{
                        ArrayList<int[]> move = new ArrayList<>();
                        move.add(new int[]{0,0});
                        move.add(pawnLocation);
                        move.add(new int[]{nx,ny});
                        allMoves.add(move);
                        nx++;
                        ny++;
                    }
                }

                //szukanie bicia dol prawo

                nx = x+1;
                ny = y-1;
                while(nx>0 && ny>0){
                    if(this.fields[nx][ny].getPawnColor()==pawnColor){
                        break;
                    }
                    if(this.fields[nx][ny].getPawnColor()==enemyPawnColor && this.fields[nx+1][ny-1].getPawnColor()==null){
                        ArrayList<int[]> move = new ArrayList<>();
                        int moreways = 0;
                        this.fields[nx+1][ny-1].setOccupied(this.fields[x][y].getOccupied());
                        ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{nx+1,ny-1});
                        for(ArrayList<int[]> mv : fullMove){
                            if(mv.get(0)[0]>0){
                                moreways++;
                                move.add(new int[]{mv.get(0)[0]+1, mv.get(0)[0]+1});
                                move.add(pawnLocation);
                                for(int i = 1; i<mv.size();i++){
                                    move.add(mv.get(i));
                                }
                                allMoves.add(move);
                            }
                        }
                        if(moreways==0){
                            move.add(new int[]{1,1});
                            move.add(pawnLocation);
                            move.add(new int[]{nx+1,ny-1});
                        }
                        this.fields[nx+1][ny-1].setOccupied(null);
                    }
                    else{
                        ArrayList<int[]> move = new ArrayList<>();
                        move.add(new int[]{0,0});
                        move.add(pawnLocation);
                        move.add(new int[]{nx,ny});
                        allMoves.add(move);
                        nx++;
                        ny--;
                    }
                }
        }
        return allMoves;
    }

}
