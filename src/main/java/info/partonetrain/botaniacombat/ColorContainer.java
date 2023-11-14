package info.partonetrain.botaniacombat;

public class ColorContainer{ //this class exists to distribute the colors differently depending on how many colors there are.

    static final int DEFAULT_COLOR = 0x20FF20;

    int[] colors = new int[]{DEFAULT_COLOR,DEFAULT_COLOR,DEFAULT_COLOR,DEFAULT_COLOR,DEFAULT_COLOR,DEFAULT_COLOR,DEFAULT_COLOR};

    public int getColorAt(int index){
        return colors[index];
    }

    public ColorContainer(String username){
        if(PsiContributorColors.isContributor(username.toLowerCase())){
            int[] contributorColors = PsiContributorColors.getColors(username.toLowerCase());
            BotaniaCombat.LOGGER.info(String.valueOf(contributorColors.length));
            switch (contributorColors.length) {
                //         1                       2                 3                    4                     5                   6                    7
                case 1 ->
                        colors = new int[]{contributorColors[0], contributorColors[0], contributorColors[0], contributorColors[0], contributorColors[0], contributorColors[0], contributorColors[0]};
                case 2 ->
                        colors = new int[]{contributorColors[1], contributorColors[1], contributorColors[0], contributorColors[0], contributorColors[0], contributorColors[1], contributorColors[1]};
                case 3 ->
                        colors = new int[]{contributorColors[2], contributorColors[2], contributorColors[1], contributorColors[0], contributorColors[1], contributorColors[2], contributorColors[2]};
                case 4 ->
                        colors = new int[]{contributorColors[3], contributorColors[2], contributorColors[1], contributorColors[0], contributorColors[1], contributorColors[2], contributorColors[3]};
                case 5 ->
                        colors = new int[]{contributorColors[0], contributorColors[1], contributorColors[2], contributorColors[2], contributorColors[2], contributorColors[3], contributorColors[4]};
                case 6 ->
                        colors = new int[]{contributorColors[0], contributorColors[1], contributorColors[2], contributorColors[3], contributorColors[2], contributorColors[4], contributorColors[5]};
                case 7 ->
                        colors = new int[]{contributorColors[0], contributorColors[1], contributorColors[2], contributorColors[3], contributorColors[4], contributorColors[5], contributorColors[6]};
            }
        }
        //otherwise it will all be default color
    }
}