package GrainGrowth;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class MainInterface extends javax.swing.JFrame {

    /// Array X size
    static int sizeX = Constants.boardWidth;
    
    /// Array Y size
    static int sizeY = Constants.boardWidth;              
    double dT = 0;
    HandlerClass handler = new HandlerClass();
    Thread thread;
    Grain boardGrain[][];
    boolean shouldRunSimulationLoop;
    private Board board;
    private boolean isMonteCarlo;
    private MonteCarlo monteCarlo;
    private int iterationNumber;

    public MainInterface() {
        iterationNumber = 0;
        monteCarlo = new MonteCarlo();
        isMonteCarlo = false;
        this.generateNewBoard();
        initComponents();
        ConditionsComboBox.setVisible(false);
        jLabel10.setVisible(false);
        recrystallizationSlider.setVisible(false);
        recrystalButton.setVisible(false);
        jLabel11.setVisible(false);
        MonteCarloToogleButton.setVisible(false);
        
        this.jLabel14.setVisible(false);
        this.jPanel5.setVisible(false);
        this.jPanel7.setVisible(false);
        this.jPanel8.setVisible(false);
        
        /// Sets numebr of grians
        jLabel9.setText("" + board.getCountGrainsCristal());    
        canvas.addMouseListener(handler);
        canvas.addMouseMotionListener(handler);
        jLabel11.setText(recrystallizationSlider.getValue() + " %");
    }
    
    private void generateNewBoard() {
        board = new Board(sizeX, sizeY);
        boardGrain = new Grain[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                boardGrain[i][j] = new Grain(); /// Filling the board             
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        xLabelSize = new javax.swing.JLabel();
        YLabelSize = new javax.swing.JLabel();
        countXText = new javax.swing.JTextField();
        countYText = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        idLabel = new javax.swing.JLabel();
        colorValueLabel = new javax.swing.JLabel();
        colorPanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        ringSizeField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        amountRingsField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        MonteCarloToogleButton = new javax.swing.JToggleButton();
        StartButton = new javax.swing.JButton();
        StopButton = new javax.swing.JButton();
        NeighborhoodComboBox = new javax.swing.JComboBox();
        ConditionsComboBox = new javax.swing.JComboBox();
        GenerateButton = new javax.swing.JButton();
        ClearButton = new javax.swing.JButton();
        NeighborhoodLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        BCsButton = new javax.swing.JToggleButton();
        recrystalButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        recrystallizationSlider = new javax.swing.JSlider();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        randomSeedsCountText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        inclusionsAmount = new javax.swing.JTextField();
        inclusionsSize = new javax.swing.JTextField();
        inclusionShapeComboBox = new javax.swing.JComboBox<String>();
        addInclusionsButton = new javax.swing.JButton();
        radiusLabel = new javax.swing.JLabel();
        radiusText = new javax.swing.JTextField();
        canvas = new GrainGrowth.Canvas();
        jMenuBar1 = new javax.swing.JMenuBar();
        ImportMenu = new javax.swing.JMenu();
        FromBitmapImport = new javax.swing.JMenuItem();
        FromTextFileImport = new javax.swing.JMenuItem();
        ExportMenu = new javax.swing.JMenu();
        ToBitmapExport = new javax.swing.JMenuItem();
        ToTextFileExport = new javax.swing.JMenuItem();
        showGrainsBorders = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(950, 650));
        setMinimumSize(new java.awt.Dimension(950, 650));
        setPreferredSize(new java.awt.Dimension(950, 650));
        setSize(new java.awt.Dimension(950, 650));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(950, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(950, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(1150, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel8.setText("Nucleons count:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 2);
        jPanel9.add(jLabel8, gridBagConstraints);

        jLabel9.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel9.add(jLabel9, gridBagConstraints);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSeparator2.setMinimumSize(new java.awt.Dimension(1, 20));
        jSeparator2.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel9.add(jSeparator2, gridBagConstraints);

        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSeparator1.setMinimumSize(new java.awt.Dimension(1, 20));
        jSeparator1.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel9.add(jSeparator1, gridBagConstraints);

        jLabel12.setText("Iteration: 0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel9.add(jLabel12, gridBagConstraints);

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 520, -1, -1));

        jPanel2.setLayout(new java.awt.GridBagLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel6.setLayout(new java.awt.GridBagLayout());

        xLabelSize.setText("X:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(xLabelSize, gridBagConstraints);

        YLabelSize.setText("Y:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel6.add(YLabelSize, gridBagConstraints);

        countXText.setText("500");
        countXText.setToolTipText("");
        countXText.setMinimumSize(new java.awt.Dimension(40, 22));
        countXText.setPreferredSize(new java.awt.Dimension(40, 22));
        countXText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countXTextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(countXText, gridBagConstraints);

        countYText.setText("500");
        countYText.setMinimumSize(new java.awt.Dimension(40, 22));
        countYText.setPreferredSize(new java.awt.Dimension(40, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel6.add(countYText, gridBagConstraints);

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 280, -1, -1));

        jPanel5.setLayout(new java.awt.GridBagLayout());
        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 400, -1, -1));

        jPanel4.setLayout(new java.awt.GridBagLayout());

        idLabel.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel4.add(idLabel, gridBagConstraints);

        colorValueLabel.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        colorValueLabel.setText("(0,0,0)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel4.add(colorValueLabel, gridBagConstraints);

        colorPanel.setMinimumSize(new java.awt.Dimension(70, 25));
        colorPanel.setPreferredSize(new java.awt.Dimension(70, 25));
        colorPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel4.add(colorPanel, gridBagConstraints);

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, -1, -1));

        jPanel7.setLayout(new java.awt.GridBagLayout());

        ringSizeField.setText("5");
        ringSizeField.setMinimumSize(new java.awt.Dimension(40, 22));
        ringSizeField.setName(""); // NOI18N
        ringSizeField.setPreferredSize(new java.awt.Dimension(40, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 5, 5);
        jPanel7.add(ringSizeField, gridBagConstraints);

        jLabel4.setText("Promień:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel7.add(jLabel4, gridBagConstraints);

        amountRingsField.setText("5");
        amountRingsField.setMinimumSize(new java.awt.Dimension(40, 22));
        amountRingsField.setPreferredSize(new java.awt.Dimension(40, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel7.add(amountRingsField, gridBagConstraints);

        jLabel5.setText("Ilość pkt:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 5);
        jPanel7.add(jLabel5, gridBagConstraints);

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 170, -1, -1));

        jPanel8.setLayout(new java.awt.GridBagLayout());
        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 300, -1, -1));

        MonteCarloToogleButton.setText("Monte Carlo");
        MonteCarloToogleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonteCarloToogleButtonActionPerformed(evt);
            }
        });
        jPanel1.add(MonteCarloToogleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, 150, 140));

        StartButton.setText("Start");
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });
        jPanel1.add(StartButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, 280, 60));

        StopButton.setText("Stop");
        StopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopButtonActionPerformed(evt);
            }
        });
        jPanel1.add(StopButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 280, 60));

        NeighborhoodComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Moore", "von Neumann'a", "-", "-", "-", "-", "-", "-", "-" }));
        NeighborhoodComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                NeighborhoodComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        NeighborhoodComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NeighborhoodComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(NeighborhoodComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 340, 280, -1));

        ConditionsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Losowe", "Stała odległość", "Stała ilość ziaren" }));
        ConditionsComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                ConditionsComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel1.add(ConditionsComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 310, 160, 30));

        GenerateButton.setText("Generate");
        GenerateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateButtonActionPerformed(evt);
            }
        });
        jPanel1.add(GenerateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 280, 60));

        ClearButton.setText("Clear");
        ClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ClearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 220, 280, 60));

        NeighborhoodLabel.setText("Neighborhood");
        jPanel1.add(NeighborhoodLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 320, 280, -1));

        jLabel14.setText("Conditions");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 290, -1, -1));

        BCsButton.setText("Perdiodic BC");
        BCsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCsButtonActionPerformed(evt);
            }
        });
        jPanel10.add(BCsButton);

        recrystalButton.setText("Rekrystalizacja");
        recrystalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recrystalButtonActionPerformed(evt);
            }
        });
        jPanel10.add(recrystalButton);

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 380, 230, 40));

        jLabel10.setText("Recristalize: ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 360, -1, -1));

        recrystallizationSlider.setMinimumSize(new java.awt.Dimension(100, 16));
        recrystallizationSlider.setPreferredSize(new java.awt.Dimension(100, 16));
        recrystallizationSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                recrystallizationSliderStateChanged(evt);
            }
        });
        jPanel1.add(recrystallizationSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 360, -1, -1));

        jLabel11.setText("0 %");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 360, -1, -1));

        jLabel3.setText("Nucleons count");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, -1, -1));

        randomSeedsCountText.setText("10");
        randomSeedsCountText.setMinimumSize(new java.awt.Dimension(40, 22));
        randomSeedsCountText.setPreferredSize(new java.awt.Dimension(40, 22));
        jPanel1.add(randomSeedsCountText, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 290, -1, -1));

        jLabel1.setText("Amount of inclusions ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 380, -1, -1));

        jLabel2.setText("Size of inclusions");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 440, -1, -1));

        jLabel6.setText("inclusions shape");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 430, -1, -1));

        inclusionsAmount.setText("10");
        inclusionsAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inclusionsAmountActionPerformed(evt);
            }
        });
        jPanel1.add(inclusionsAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 400, 280, -1));

        inclusionsSize.setText("5");
        inclusionsSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inclusionsSizeActionPerformed(evt);
            }
        });
        jPanel1.add(inclusionsSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 460, 130, -1));

        inclusionShapeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "square", "circular" }));
        inclusionShapeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inclusionShapeComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(inclusionShapeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 460, 130, -1));

        addInclusionsButton.setText("Add Inclusions");
        addInclusionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInclusionsButtonActionPerformed(evt);
            }
        });
        jPanel1.add(addInclusionsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 500, 280, 60));

        radiusLabel.setText("Promień:");
        jPanel1.add(radiusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 170, -1, 22));

        radiusText.setText("10");
        radiusText.setMinimumSize(new java.awt.Dimension(40, 22));
        radiusText.setPreferredSize(new java.awt.Dimension(40, 22));
        radiusText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiusTextActionPerformed(evt);
            }
        });
        jPanel1.add(radiusText, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 170, -1, -1));

        canvas.setDoubleBuffered(true);
        canvas.setPreferredSize(new java.awt.Dimension(620, 500));
        jPanel1.add(canvas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 630, 500));

        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());

        ImportMenu.setText("Import");
        ImportMenu.setToolTipText("");

        FromBitmapImport.setText("From Bitmap");
        FromBitmapImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FromBitmapImportActionPerformed(evt);
            }
        });
        ImportMenu.add(FromBitmapImport);

        FromTextFileImport.setText("From TextFile");
        FromTextFileImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FromTextFileImportActionPerformed(evt);
            }
        });
        ImportMenu.add(FromTextFileImport);

        jMenuBar1.add(ImportMenu);

        ExportMenu.setText("Export");

        ToBitmapExport.setText("To bitmap");
        ToBitmapExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToBitmapExportActionPerformed(evt);
            }
        });
        ExportMenu.add(ToBitmapExport);

        ToTextFileExport.setText("To text file");
        ToTextFileExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToTextFileExportActionPerformed(evt);
            }
        });
        ExportMenu.add(ToTextFileExport);

        showGrainsBorders.setSelected(true);
        showGrainsBorders.setText("Show grains boarders");
        ExportMenu.add(showGrainsBorders);

        jMenuBar1.add(ExportMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private BufferedImage map( int sizeX, int sizeY, boolean showBoundary){
    final BufferedImage res = new BufferedImage( sizeX, sizeY, BufferedImage.TYPE_INT_RGB );
            for(int j=0;j<MainInterface.sizeY;j++){
            for(int i=0;i<MainInterface.sizeX;i++){
                if(boardGrain[i][j].getId() == 0 || (boardGrain[i][j].isBoundary()) && showBoundary ){
                    res.setRGB(i,j, Color.BLACK.getRGB() );
                }else{
                    int R = boardGrain[i][j].getRColorParameter();
                    int G = boardGrain[i][j].getGColorParameter();
                    int B = boardGrain[i][j].getBColorParameter();
                    int grain = boardGrain[i][j].getId();
              
                    res.setRGB(i,j, new Color(R,G,B).getRGB() );
                }
            } 
        }
        return res;
    }
    
    private static void saveBMP( final BufferedImage bi, final String path ){
        try {
            RenderedImage rendImage = bi;
            ImageIO.write(rendImage, "bmp", new File(path));
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

    private void ToBitmapExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToBitmapExportActionPerformed
        BufferedImage img = map(sizeX, sizeY, showGrainsBorders.isSelected());
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");   
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            saveBMP( img, fileToSave.getAbsolutePath() + ".bmp" );
        }
    }//GEN-LAST:event_ToBitmapExportActionPerformed

    private void recrystallizationSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_recrystallizationSliderStateChanged
        jLabel11.setText(recrystallizationSlider.getValue() + " %");
        board.setRecrystalPercent(recrystallizationSlider.getValue());
    }//GEN-LAST:event_recrystallizationSliderStateChanged

    private void recrystalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recrystalButtonActionPerformed
        iterationNumber = 0;
        jLabel12.setText("Iteration: " + iterationNumber);
        if (thread != null) {
            thread.stop();
        }

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startRecristalizationSimulation();
            }
        });
        thread.start();
    }//GEN-LAST:event_recrystalButtonActionPerformed

    private void BCsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCsButtonActionPerformed
        board.changePeriodic();
        monteCarlo.changePerio();
    }//GEN-LAST:event_BCsButtonActionPerformed

    private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed
        iterationNumber = 0;
        jLabel12.setText("Iteration: " + iterationNumber);
        boardGrain = board.clear();
        canvas.setGrains(boardGrain);
        canvas.repaint();
        jLabel9.setText("" + board.getCountGrainsCristal());
    }//GEN-LAST:event_ClearButtonActionPerformed

    private void GenerateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateButtonActionPerformed
        canvas.resizeBoard(Integer.parseInt(countXText.getText()), Integer.parseInt(countYText.getText()));
        canvas.setPreferredSize(new java.awt.Dimension(Integer.parseInt(countXText.getText()), Integer.parseInt(countYText.getText())));
        canvas.setSize(Integer.parseInt(countXText.getText()), Integer.parseInt(countYText.getText()));
        
        sizeX = Integer.parseInt(countXText.getText());
        sizeY = Integer.parseInt(countYText.getText());
        this.generateNewBoard();
        
        if (isMonteCarlo) {
            monteCarlo = new MonteCarlo(sizeX, sizeY, Integer.parseInt(randomSeedsCountText.getText()));
            boardGrain = monteCarlo.randomBoard();
            canvas.setGrains(boardGrain);
            canvas.repaint();
        } else {
            boardGrain = board.clear();           
            boardGrain = board.randomBoard(3,  
                Integer.parseInt(countXText.getText()),                 
                Integer.parseInt(countYText.getText()),                
                Integer.parseInt(randomSeedsCountText.getText()),
                1,              
                Integer.parseInt(randomSeedsCountText.getText()));          
            canvas.setGrains(boardGrain);                                  
            canvas.repaint();                                              
            jLabel9.setText("" + board.getCountGrainsCristal());
        }
    }//GEN-LAST:event_GenerateButtonActionPerformed

    private void ConditionsComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_ConditionsComboBoxPopupMenuWillBecomeInvisible
        switch (ConditionsComboBox.getSelectedIndex()) {
            case 0:
            this.jPanel5.setVisible(false);
            this.jPanel6.setVisible(false);
            this.jPanel7.setVisible(false);
            break;
            case 2:
            this.jPanel5.setVisible(true);
            this.jPanel6.setVisible(false);
            this.jPanel7.setVisible(false);
            break;
            case 1:
            this.jPanel5.setVisible(false);
            this.jPanel6.setVisible(true);
            this.jPanel7.setVisible(false);
            break;
            case 3:
            this.jPanel5.setVisible(false);
            this.jPanel6.setVisible(false);
            this.jPanel7.setVisible(true);
            break;
            default:
            break;
        }
    }//GEN-LAST:event_ConditionsComboBoxPopupMenuWillBecomeInvisible

    private void NeighborhoodComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NeighborhoodComboBoxActionPerformed

    }//GEN-LAST:event_NeighborhoodComboBoxActionPerformed

    private void NeighborhoodComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_NeighborhoodComboBoxPopupMenuWillBecomeInvisible
        switch (NeighborhoodComboBox.getSelectedIndex()) {
            case 7:
            this.jPanel8.setVisible(true);
            break;
            default:
            this.jPanel8.setVisible(false);
            break;
        }
    }//GEN-LAST:event_NeighborhoodComboBoxPopupMenuWillBecomeInvisible

    private void StopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopButtonActionPerformed
        if (thread != null) {
            thread.stop();
        }
    }//GEN-LAST:event_StopButtonActionPerformed

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        iterationNumber = 0;
        jLabel12.setText("Iteration: " + iterationNumber);
        if (thread != null) {          
            thread.stop();
        }
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startSimulation();
            }
        });
        thread.start();
    }//GEN-LAST:event_StartButtonActionPerformed

    private void MonteCarloToogleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonteCarloToogleButtonActionPerformed
        isMonteCarlo = !isMonteCarlo;
        this.jPanel5.setVisible(isMonteCarlo);
    }//GEN-LAST:event_MonteCarloToogleButtonActionPerformed
    
    private void FromBitmapImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FromBitmapImportActionPerformed
        try {
            //openFile; 
            HashSet<Color> treeSet = new HashSet<>();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to import");   
            int userSelection = fileChooser.showSaveDialog(this);
           
            File bmpFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
            BufferedImage image = ImageIO.read(bmpFile);
            if(image.getHeight()== sizeY && image.getWidth() == sizeX);
            {
                for(int y=0;y<sizeY;y++){
                    for(int x=0;x<sizeX;x++){
                        int clr=  image.getRGB(x,y); 
                        int  red   = (clr & 0x00ff0000) >> 16;
                        int  green = (clr & 0x0000ff00) >> 8;
                        int  blue  =  clr & 0x000000ff;
                        boardGrain[x][y].setRGB(red, green, blue);
                        if( red == 255 && blue == 255 && green == 255) {
                            boardGrain[x][y].setId(-1);
                            continue;
                        }
                        treeSet.add(new Color(red,green,blue));
                    }
                }
            }
            
            HashMap<Color, Integer> colorMapping = new HashMap<Color, Integer>();
            int uniq_id = 1;
            Color white = new Color(255,255,255);
            for(Color cl : treeSet)
            {
                if(cl == white)
                {
                    System.err.println("Colour is White");
                    colorMapping.put(cl, -1);
                }
                else
                {
                    colorMapping.put(cl, uniq_id);
                    uniq_id++;
                }
            }
            for(int y=0;y<sizeY;y++){
                for(int x=0;x<sizeX;x++){
                    if (boardGrain[x][y].getId() != -1)
                        boardGrain[x][y].setId(colorMapping.get(new Color(boardGrain[x][y].getRColorParameter(),boardGrain[x][y].getGColorParameter(),boardGrain[x][y].getBColorParameter()))); 
                    }
            }
            boardGrain = board.edge();
            canvas.setGrains(boardGrain);
            canvas.repaint();
        } catch (IOException ex) {
            Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_FromBitmapImportActionPerformed

    private void ToTextFileExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToTextFileExportActionPerformed
        PrintWriter writer = null;
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a text file to save");            
            int userSelection = fileChooser.showSaveDialog(this);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = fileChooser.getSelectedFile();
                writer = new PrintWriter(fileToOpen, "UTF-8");
                
                for (int y = 0; y < sizeY; y++) {                    
                    for (int x = 0; x < sizeX; x++) {
                        writer.println(boardGrain[x][y].getId() + ","
                                + x + "," + y + ","
                                + boardGrain[x][y].getRColorParameter() + ","
                                + boardGrain[x][y].getGColorParameter() + ","
                                + boardGrain[x][y].getBColorParameter());
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
    }//GEN-LAST:event_ToTextFileExportActionPerformed

    private void FromTextFileImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FromTextFileImportActionPerformed

            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to Open");
                int userSelection = fileChooser.showSaveDialog(this);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToOpen = fileChooser.getSelectedFile();
                    BufferedReader in = new BufferedReader(new FileReader(fileToOpen));
                    while (true) {
                        String line = in.readLine();
                        if (line == null) {
                            break;
                        }
                        String[] propperties = line.split(",");
                        int id = Integer.parseInt(propperties[0]);
                        int x = Integer.parseInt(propperties[1]);
                        int y = Integer.parseInt(propperties[2]);
                        int R = Integer.parseInt(propperties[3]);
                        int G = Integer.parseInt(propperties[4]);
                        int B = Integer.parseInt(propperties[5]);
                        boardGrain[x][y].setId(id);
                        boardGrain[x][y].setRGB(R, G, B);
                    }
                }
        } catch (HeadlessException headlessException) {
        } catch (FileNotFoundException fileNotFoundException) {
        } catch (IOException ex) {
                    Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            boardGrain = board.edge();
            canvas.setGrains(boardGrain);
            canvas.repaint();
    }//GEN-LAST:event_FromTextFileImportActionPerformed

    private void inclusionsAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inclusionsAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inclusionsAmountActionPerformed

    private void inclusionsSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inclusionsSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inclusionsSizeActionPerformed

    private void inclusionShapeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inclusionShapeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inclusionShapeComboBoxActionPerformed

    private void addInclusionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInclusionsButtonActionPerformed
        int size = Integer.parseInt(inclusionsSize.getText());
        int amount = Integer.parseInt(inclusionsAmount.getText());
        int shape = inclusionShapeComboBox.getSelectedIndex();
        int x0 = 0;
        int y0 = 0;
        Random rand = new Random();
       
        ArrayList<Grain> grainList = board.getGrainsOnBorder();

        for(int attempt = 0; attempt< amount; attempt++)
        {
            if(board.isEdgeGenerated()){
                int randomGrainIndex = rand.nextInt(grainList.size());
                Grain randomGrain = grainList.get(randomGrainIndex);  
                x0 = randomGrain.getX();
                y0 = randomGrain.getY();
            }
            else {
                x0 = rand.nextInt(sizeX);
                y0 = rand.nextInt(sizeY);
            }
            
            if (shape == 0) {
                int a = (int)((size/1.44)/2);    
                for(int stepX = -a; stepX <a; stepX++) {
                    for(int stepY = -a; stepY <a; stepY++) {
                        if(x0+stepX < sizeX && x0+stepX > 0 && y0+stepY < sizeY && y0+stepY >0 ) {
                            boardGrain[x0+stepX][y0+stepY].setId(-1);
                            boardGrain[x0+stepX][y0+stepY].setRGB(255,255,255);
                        }
                    }
                }
            }
            else {
                for(int y=-size; y<=size; y++)
                    for(int x=-size; x<=size; x++)
                        if(x*x+y*y <= size*size && x0+x < sizeX && x0+x>0 && y0+y < sizeY && y0+y>0)
                            boardGrain[x0+x][y0+y].setId(-1);
            }       
        }
        boardGrain = board.edge();
        canvas.setGrains(boardGrain);
        canvas.repaint();
        
    }//GEN-LAST:event_addInclusionsButtonActionPerformed

    private void radiusTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiusTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radiusTextActionPerformed

    private void countXTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countXTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_countXTextActionPerformed

    private void startSimulation() {
        if (isMonteCarlo) {
            shouldRunSimulationLoop = true;
            while (shouldRunSimulationLoop) {
                iterationNumber++;
                jLabel12.setText("Iteration: " + iterationNumber);
                boardGrain = monteCarlo.calculate();
                canvas.setGrains(boardGrain);
                canvas.repaint();
                if (monteCarlo.getChanged() == 0) {
                    shouldRunSimulationLoop = false;
                }
            }
        } else {
            shouldRunSimulationLoop = true;
            while (shouldRunSimulationLoop) {
                iterationNumber++;
                jLabel12.setText("Iteration: " + iterationNumber);
                boardGrain = board.calculate(
                    NeighborhoodComboBox.getSelectedIndex(),
                    Integer.parseInt(radiusText.getText())
                );
                shouldRunSimulationLoop = board.ammountOfNotEmptyCells() != (sizeX * sizeY);
                canvas.setGrains(boardGrain);
                canvas.repaint();

                if (!shouldRunSimulationLoop) {
                    boardGrain = board.edge();
                    jLabel9.setText("" + board.getCountGrainsCristal());
                    thread.stop();
                }
            }
        }
    }

    private void startRecristalizationSimulation() {

        if (isMonteCarlo) {
            monteCarlo = new MonteCarlo(sizeX, sizeY, boardGrain);
            shouldRunSimulationLoop = true;
            while (shouldRunSimulationLoop) {
                iterationNumber++;
                jLabel12.setText("Iteration: " + iterationNumber);
                boardGrain = monteCarlo.calculate();
                canvas.setGrains(boardGrain);
                canvas.repaint();
                if (monteCarlo.getChanged() == 0) {
                    shouldRunSimulationLoop = false;
                }
            }
        } else {
            shouldRunSimulationLoop = true;
            while (shouldRunSimulationLoop) {
                iterationNumber++;
                jLabel12.setText("Iteration: " + iterationNumber);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
                dT += 0.001;
                boardGrain = board.recristalizationCalculate(NeighborhoodComboBox.getSelectedIndex(), dT);
                shouldRunSimulationLoop = board.recrystal() != (sizeX * sizeY);
                canvas.setGrains(boardGrain);
                canvas.repaint();
                jLabel9.setText("" + board.getCountGrainsRecristal());
                if (!shouldRunSimulationLoop) {
                    boardGrain = board.edge();
                    board.clearRecrystal();
                    thread.stop();
                }
            }
        }

    }

    public class HandlerClass implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            mouseC(e.getX(), e.getY());
        }

        int xPres, yPres;

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mauseM(e.getX(), e.getY());
        }

    }

    private void mauseM(int i, int j) {
        if (i < sizeX && j < sizeY) {
            int R = boardGrain[i][j].getRColorParameter();
            int G = boardGrain[i][j].getGColorParameter();
            int B = boardGrain[i][j].getBColorParameter();
            idLabel.setText("" + boardGrain[i][j].getId());
            colorValueLabel.setText("(" + R + "," + G + "," + B + ")" + "     " + boardGrain[i][j].isBoundary());
            colorPanel.setBackground(new Color(R, G, B));
        }
    }

    private void mouseC(int x, int y) {
        int xp = (int) Math.floor(x / (Constants.boardWidth / sizeX));
        int yp = (int) Math.floor(y / (Constants.boardHeight / sizeY));
        boardGrain = board.addGrain(xp, yp);
        canvas.setGrains(boardGrain);
        canvas.repaint();
        jLabel9.setText("" + board.getCountGrainsCristal());
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BCsButton;
    private javax.swing.JButton ClearButton;
    private javax.swing.JComboBox ConditionsComboBox;
    private javax.swing.JMenu ExportMenu;
    private javax.swing.JMenuItem FromBitmapImport;
    private javax.swing.JMenuItem FromTextFileImport;
    private javax.swing.JButton GenerateButton;
    private javax.swing.JMenu ImportMenu;
    private javax.swing.JToggleButton MonteCarloToogleButton;
    private javax.swing.JComboBox NeighborhoodComboBox;
    private javax.swing.JLabel NeighborhoodLabel;
    private javax.swing.JButton StartButton;
    private javax.swing.JButton StopButton;
    private javax.swing.JMenuItem ToBitmapExport;
    private javax.swing.JMenuItem ToTextFileExport;
    private javax.swing.JLabel YLabelSize;
    private javax.swing.JButton addInclusionsButton;
    private javax.swing.JTextField amountRingsField;
    private GrainGrowth.Canvas canvas;
    private javax.swing.JPanel colorPanel;
    private javax.swing.JLabel colorValueLabel;
    private javax.swing.JTextField countXText;
    private javax.swing.JTextField countYText;
    private javax.swing.JLabel idLabel;
    private javax.swing.JComboBox<String> inclusionShapeComboBox;
    private javax.swing.JTextField inclusionsAmount;
    private javax.swing.JTextField inclusionsSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel radiusLabel;
    private javax.swing.JTextField radiusText;
    private javax.swing.JTextField randomSeedsCountText;
    private javax.swing.JButton recrystalButton;
    private javax.swing.JSlider recrystallizationSlider;
    private javax.swing.JTextField ringSizeField;
    private javax.swing.JRadioButtonMenuItem showGrainsBorders;
    private javax.swing.JLabel xLabelSize;
    // End of variables declaration//GEN-END:variables
}
